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
import entidades.Producto;
import entidades.Usuario;
import enums.EstadoPedido;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public PedidoDTO buscarPorId(Long idPedido) {
        Pedido pedido = pedidoDAO.buscarPorId(idPedido); 
        return convertirPedidoADTO(pedido);
    }

    /**
     * Consulta todos los pedidos
     */
    public List<PedidoDTO> consultarPedidos() {
        List<Pedido> pedidosEntidad = pedidoDAO.buscarTodosLosPedidos(); 

        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidosEntidad) {
            pedidosDTO.add(convertirPedidoADTO(pedido));
        }
        return pedidosDTO;
    }

    public List<PedidoDTO> buscarPorNombre(String nombre) {
        List<Pedido> pedidosEntidad = pedidoDAO.buscarPorIdPedidoOIdUsuario(nombre); 

        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidosEntidad) {
            pedidosDTO.add(convertirPedidoADTO(pedido));
        }
        return pedidosDTO;
    }

    /**
     * Busca un pedido por su ID y actualiza su estado a 'CANCELADO'.
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

    // --- Métodos de Conversión a DTO ---
    private PedidoDTO convertirPedidoADTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoDTO dto = new PedidoDTO();

        dto.setIdPedido(pedido.getIdPedido());
        dto.setEstadoPedido(pedido.getEstadoPedido().name());

        if (pedido.getFechaHora() != null) {
            Date fechaConvertida = Date.from(pedido.getFechaHora()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            dto.setFechaHora(fechaConvertida);
        }

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
        if (usuario.getRol() != null) {
            dto.setRol(usuario.getRol().toString());
        }

        return dto;
    }

    private PagoDTO convertirPagoADTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();
        dto.setIdPago(pago.getIdPago());
        dto.setMonto(pago.getMonto());
        if (pago.getMetodoPago() != null) {
            dto.setMetodoPago(pago.getMetodoPago().toString());
        }
        if (pago.getEstadoPago() != null) {
            dto.setEstadoPago(pago.getEstadoPago().toString());
        }

        if (pago.getReferencia() == null || pago.getReferencia().trim().isEmpty()) {
            dto.setReferencia(" No Disponible");
        } else {
            dto.setReferencia(pago.getReferencia());
        }

        return dto;
    }

    private List<ItemDTO> convertirItemsA_DTO(List<Item> items) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }

        List<ItemDTO> dtos = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDto = new ItemDTO();

            itemDto.setIdItem(item.getIdItem());
            itemDto.setCantidad(item.getCantidad());
            itemDto.setPrecioUnitario(item.getPrecioUnitario());

            Producto producto = item.getProducto();
            if (producto != null) {
                itemDto.setIdProducto(producto.getIdProducto());
                itemDto.setNombreProducto(producto.getNombreProducto());
                itemDto.setImagenUrl(producto.getImagenUrl());
            }

            dtos.add(itemDto);
        }
        return dtos;
    }
}
