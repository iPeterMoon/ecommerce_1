package api;

import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import DAO.interfaces.IPedidoDAO;
import DAO.interfaces.IUsuarioDAO;
import DTO.PedidoDTO;
import entidades.Pedido;
import entidades.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import mappers.PedidoMapper;
import util.JwtUtil; // Importamos tu utilidad

/**
 * Recurso REST para gestionar pedidos.
 */
@Path("mis-pedidos")
@RequestScoped
public class HistoryResource {

    @Context
    private HttpServletRequest request;

    private IPedidoDAO pedidoDAO = new PedidoDAO();
    private IUsuarioDAO usuarioDAO = new UsuarioDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMisPedidos() {
        try {
            // 1. Obtener el token del Header Authorization
            String authHeader = request.getHeader("Authorization");
            String token = JwtUtil.extraerTokenDelHeader(authHeader);

            // 2. Validar que el token exista y sea válido
            if (token == null || !JwtUtil.validarToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"Token inválido o no proporcionado\"}")
                        .build();
            }

            // 3. Extraer el userId directamente del token usando JwtUtil
            Long userId = JwtUtil.extraerUserId(token);

            if (userId == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"Token no contiene un ID de usuario válido\"}")
                        .build();
            }

            // 4. Buscar la entidad Usuario (necesaria para el DAO de pedidos)
            Usuario usuario = usuarioDAO.buscarPorId(userId);
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Usuario no encontrado\"}")
                        .build();
            }

            // 5. Buscar los pedidos de ese usuario
            List<Pedido> pedidos = pedidoDAO.buscarPorUsuario(usuario);

            // 6. Convertir Entidades a DTOs usando el Mapper
            List<PedidoDTO> pedidosDTO = pedidos.stream()
                    .map(PedidoMapper::toDTO)
                    .collect(Collectors.toList());

            return Response.ok(pedidosDTO).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al obtener el historial: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}