package api;

import DAO.UsuarioDAO;
import DAO.interfaces.IUsuarioDAO;
import entidades.Direccion;
import entidades.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import util.JwtUtil;

@Path("auth/me")
@RequestScoped
public class ProfileResource {

    private IUsuarioDAO usuarioDAO = new UsuarioDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(@Context HttpHeaders headers) {
        try {
            String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            String token = authHeader.substring(7);

            if (!JwtUtil.validarToken(token)) {
                 return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            Long userId = JwtUtil.extraerUserId(token);
            Usuario usuario = usuarioDAO.buscarPorId(userId);

            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            String calleStr = "";
            String ciudadStr = "";
            String cpStr = "";
            
            List<Direccion> direcciones = usuario.getDirecciones();
            
            if (direcciones != null && !direcciones.isEmpty()) {
                Direccion d = direcciones.get(0);
                calleStr = d.getCalle() + " " + d.getNumeroExterior(); 
                ciudadStr = d.getCiudad();
                cpStr = d.getCodigoPostal();
            }

            String jsonResponse = String.format(
                "{\"nombre\": \"%s\", \"direccion\": \"%s\", \"ciudad\": \"%s\", \"cp\": \"%s\"}",
                usuario.getNombre().replace("\"", ""), 
                calleStr.replace("\"", ""), 
                ciudadStr.replace("\"", ""), 
                cpStr.replace("\"", "")
            );

            return Response.ok(jsonResponse).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al obtener perfil\"}")
                    .build();
        }
    }
}