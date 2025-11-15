
package modelo;

import DAO.PedidoDAO;
import DAO.interfaces.IPedidoDAO;
import DTO.ItemDTO;
import DTO.PagoDTO;
import DTO.PedidoDTO;
import DTO.UsuarioDTO;
import entidades.Item;
import entidades.Pago;
import entidades.Pedido;
import entidades.Usuario;
import enums.EstadoPedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Moreno
 */
public class PedidoBO {

    IPedidoDAO pedidoDAO;

    public PedidoBO() {
        this.pedidoDAO = new PedidoDAO();
    }

    /**
     * Consulta todos los pedidos( Implementar paginacion podria ser una buena opción.)
     * @return Una lista de PedidoDTO con los pedidos.
     */
    public List<PedidoDTO> consultarPedidos() {
        List<Pedido> pedidosEntidad = pedidoDAO.buscarTodosLosPedidos();

        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidosEntidad) {
            pedidosDTO.add(convertirPedidoADTO(pedido));
        }
        return pedidosDTO;

    }

    /**
     * Busca un pedido por su ID y actualiza su estado a 'CANCELADO'.
     *
     * @param idPedido El ID del pedido a cancelar.
     */
    public void cancelarPedido(Long idPedido) {
        Pedido pedido = pedidoDAO.buscarPorId(idPedido);

        if (pedido != null) {
            pedido.setEstadoPedido(EstadoPedido.CANCELADO);

            pedidoDAO.actualizar(pedido);
        } else {
            System.err.println("No se encontró el pedido con ID: " + idPedido + " para cancelar.");
        }
    }

     public void enviarPedido(Long idPedido) {
        Pedido pedido = pedidoDAO.buscarPorId(idPedido);

        if (pedido != null) {
            pedido.setEstadoPedido(EstadoPedido.ENVIADO);

            pedidoDAO.actualizar(pedido);
        } else {
            System.err.println("No se encontró el pedido con ID: " + idPedido + " para enviar.");
        }
    }
   
    private PedidoDTO convertirPedidoADTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoDTO dto = new PedidoDTO();

        dto.setIdPedido(pedido.getIdPedido());
        dto.setEstadoPedido(pedido.getEstadoPedido().name());
        dto.setFechaHora(pedido.getFechaHora());
        dto.setUsuario(convertirUsuarioADTO(pedido.getUsuario()));
        dto.setPago(convertirPagoADTO(pedido.getPago()));
        dto.setItems(convertirItemsA_DTO(pedido.getItems()));

        return dto;
    }

    private UsuarioDTO convertirUsuarioADTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();
        
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());
        return dto;
    }

    private PagoDTO convertirPagoADTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();
        dto.setIdPago(pago.getIdPago());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago().toString());
        dto.setEstadoPago(pago.getEstadoPago().toString());
        dto.setReferencia(pago.getReferencia());
        return dto;
    }
    
    /*Aun no se utiliza, solo si se quisiese ver el detalle de cada pedido.*/
    private List<ItemDTO> convertirItemsA_DTO(List<Item> items) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>(); 
        }

        List<ItemDTO> dtos = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDto = new ItemDTO();
            // itemDto.setIdProducto(item.getProducto().getId());
            // itemDto.setNombreProducto(item.getProducto().getNombre());
            // itemDto.setCantidad(item.getCantidad());
            // itemDto.setPrecioUnitario(item.getPrecioUnitario());
            dtos.add(itemDto);
        }
        return dtos;
    }

}
