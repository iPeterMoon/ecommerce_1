package api;

import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import DAO.ProductoDAO;
import DAO.interfaces.IPedidoDAO;
import DAO.interfaces.IUsuarioDAO;
import DAO.interfaces.IProductoDAO;
import DTO.ItemDTO;
import entidades.Item;
import entidades.Pago;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import enums.EstadoPago;
import enums.EstadoPedido;
import enums.MetodoPago;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import util.JwtUtil;

@Path("orders")
@RequestScoped
public class OrderResource {

    @Context
    private HttpServletRequest request;

    private IPedidoDAO pedidoDAO = new PedidoDAO();
    private IUsuarioDAO usuarioDAO = new UsuarioDAO();
    private IProductoDAO productoDAO = new ProductoDAO(); 

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderRequest orderRequest) {
        
        try {
            String authHeader = request.getHeader("Authorization");
            
            String token = JwtUtil.extraerTokenDelHeader(authHeader);

            if (token == null || !JwtUtil.validarToken(token)) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"Sesión inválida o expirada\"}").build();
            }

            Long userId = JwtUtil.extraerUserId(token);

            HttpSession session = request.getSession(false);
            
            List<ItemDTO> cart = (session != null) ? (List<ItemDTO>) session.getAttribute("cart") : null;

            if (cart == null || cart.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"El carrito está vacío\"}").build();
            }

            Usuario usuario = usuarioDAO.buscarPorId(userId);
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Usuario no encontrado\"}").build();
            }

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setFechaHora(LocalDateTime.now());
            pedido.setEstadoPedido(EstadoPedido.PENDIENTE); 
            
            List<Item> items = new ArrayList<>();
            BigDecimal totalAmount = BigDecimal.ZERO;

            for (ItemDTO itemDTO : cart) {
                Item item = new Item();
                item.setPedido(pedido);
                item.setCantidad(itemDTO.getCantidad());
                item.setPrecioUnitario(itemDTO.getPrecioUnitario());
                
                Producto prod = productoDAO.buscarPorId(itemDTO.getIdProducto());
                if (prod != null) {
                    item.setProducto(prod);
                } else {
                }
                
                items.add(item);
                BigDecimal subtotal = itemDTO.getPrecioUnitario().multiply(new BigDecimal(itemDTO.getCantidad()));
                totalAmount = totalAmount.add(subtotal);
            }
            pedido.setItems(items);

            Pago pago = new Pago();
            pago.setMonto(totalAmount);
            pago.setFechaHora(LocalDateTime.now());
            pago.setPedido(pedido);
            
            pago.setReferencia(generarReferenciaAleatoria());
            String methodStr = orderRequest.getMetodoPago();

            try {
                pago.setMetodoPago(MetodoPago.valueOf(methodStr)); 
            } catch (Exception e) {
                 return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Método de pago inválido: " + methodStr + "\"}").build();
            }

            pago.setEstadoPago(EstadoPago.PENDIENTE); 
            pedido.setPago(pago);

            pedidoDAO.crear(pedido); 

            session.removeAttribute("cart");

            return Response.ok("{\"idPedido\": " + pedido.getIdPedido() + ", \"message\": \"Pedido creado exitosamente\"}").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al procesar el pedido: " + e.getMessage() + "\"}").build();
        }
    }

    private String generarReferenciaAleatoria() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}

class OrderRequest {
    private String metodoPago;
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}