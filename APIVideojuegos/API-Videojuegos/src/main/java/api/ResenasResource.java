/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;
import DAO.ProductoDAO;
import DAO.ResenaDAO;
import DAO.UsuarioDAO;
import DTO.ResenaDTO;
import entidades.Producto;
import entidades.Resena;
import entidades.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import util.JwtUtil;

/**
 *
 * @author Hp
 */
@Path("resenas")
@RequestScoped
public class ResenasResource {

    @Context
    private HttpServletRequest request;

    private ResenaDAO resenaDAO;
    private UsuarioDAO usuarioDAO;
    private ProductoDAO productoDAO;

    public ResenasResource() {
        this.resenaDAO = new ResenaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.productoDAO = new ProductoDAO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearResena(ResenaDTO resenaDTO) {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = JwtUtil.extraerTokenDelHeader(authHeader);

            if (token == null || !JwtUtil.validarToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"Sesión inválida o expirada. Por favor inicia sesión nuevamente.\"}")
                        .build();
            }

            Long userId = JwtUtil.extraerUserId(token);

            if (resenaDTO.getIdProducto() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"El ID del producto es obligatorio.\"}")
                        .build();
            }
            if (resenaDTO.getCalificacion() < 1 || resenaDTO.getCalificacion() > 5) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"La calificación debe ser entre 1 y 5 estrellas.\"}")
                        .build();
            }

            Usuario usuario = usuarioDAO.buscarPorId(userId);
            Producto producto = productoDAO.buscarPorId(resenaDTO.getIdProducto());

            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Usuario no encontrado.\"}")
                        .build();
            }
            if (producto == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"El videojuego/producto no existe.\"}")
                        .build();
            }

            Resena nuevaResena = new Resena();
            nuevaResena.setUsuario(usuario);
            nuevaResena.setProducto(producto);
            nuevaResena.setCalificacion(resenaDTO.getCalificacion());
            nuevaResena.setComentario(resenaDTO.getComentario());
            nuevaResena.setFecha(LocalDate.now()); 

            resenaDAO.crear(nuevaResena);

            return Response.ok("{\"mensaje\": \"Reseña publicada con éxito\"}").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Ocurrió un error en el servidor al guardar la reseña.\"}")
                    .build();
        }
    }
}
