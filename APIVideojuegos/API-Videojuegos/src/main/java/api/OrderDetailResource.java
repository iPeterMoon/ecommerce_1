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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mappers.PedidoMapper;
import util.JwtUtil;

/**
 * Recurso REST para obtener el detalle de un pedido específico.
 * URL: /api/pedidos/{id}
 */
@Path("pedidos")
@RequestScoped
public class OrderDetailResource {

    @Context
    private HttpServletRequest request;

    private IPedidoDAO pedidoDAO = new PedidoDAO();
    private IUsuarioDAO usuarioDAO = new UsuarioDAO();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDetallePedido(@PathParam("id") Long idPedido) {
        try {
            // 1. Validar Token
            String authHeader = request.getHeader("Authorization");
            String token = JwtUtil.extraerTokenDelHeader(authHeader);

            if (token == null || !JwtUtil.validarToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"No autorizado\"}").build();
            }

            Long userId = JwtUtil.extraerUserId(token);
            String rol = JwtUtil.extraerRol(token);

            // 2. Buscar el pedido usando el método optimizado del DAO
            Pedido pedido = pedidoDAO.buscarPorIdEspecifico(idPedido);

            if (pedido == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Pedido no encontrado\"}").build();
            }

            // 3. SEGURIDAD: Verificar que el pedido pertenezca al usuario que lo solicita
            // (A menos que sea ADMIN, quien podría ver cualquier pedido)
            boolean esPropietario = pedido.getUsuario().getIdUsuario().equals(userId);
            boolean esAdmin = "ADMIN".equals(rol);

            if (!esPropietario && !esAdmin) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"No tienes permiso para ver este pedido\"}").build();
            }

            // 4. Convertir a DTO y responder
            PedidoDTO pedidoDTO = PedidoMapper.toDTO(pedido);
            return Response.ok(pedidoDTO).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno: " + e.getMessage() + "\"}").build();
        }
    }
}