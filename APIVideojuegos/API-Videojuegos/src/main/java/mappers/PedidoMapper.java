package mappers;

import DTO.PedidoDTO;
import DTO.ItemDTO;
import entidades.Pedido;
import entidades.Item;
import enums.EstadoPedido;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido entity) {
        if (entity == null) return null;

        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(entity.getIdPedido());
        
        if (entity.getFechaHora() != null) {
            dto.setFechaHora(Date.from(entity.getFechaHora().atZone(ZoneId.systemDefault()).toInstant()));
        }
        
        if (entity.getEstadoPedido() != null) {
            dto.setEstadoPedido(entity.getEstadoPedido().name());
        }
        
        dto.setUsuario(UsuarioMapper.toDTO(entity.getUsuario()));
        
        if (entity.getPago() != null) {
            dto.setPago(PagoMapper.toDTO(entity.getPago()));
        }
        
        if (entity.getItems() != null) {
            List<ItemDTO> items = entity.getItems().stream()
                .map(PedidoMapper::toItemDTO)
                .collect(Collectors.toList());
            dto.setItems(items);
        } else {
            dto.setItems(Collections.emptyList());
        }

        return dto;
    }

    public static Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;

        Pedido entity = new Pedido();
        entity.setIdPedido(dto.getIdPedido());
        
        if (dto.getFechaHora() != null) {
            entity.setFechaHora(dto.getFechaHora().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
        }
        
        if (dto.getEstadoPedido() != null) {
            try {
                entity.setEstadoPedido(EstadoPedido.valueOf(dto.getEstadoPedido()));
            } catch (IllegalArgumentException e) {
            }
        }
        
        entity.setUsuario(UsuarioMapper.toEntity(dto.getUsuario()));
        
        return entity;
    }

    // Helper interno para Items
    private static ItemDTO toItemDTO(Item itemEntity) {
        if (itemEntity == null) return null;

        ItemDTO dto = new ItemDTO();
        
        if (itemEntity.getProducto() != null) {
            dto.setIdProducto(itemEntity.getProducto().getIdProducto());
            dto.setNombreProducto(itemEntity.getProducto().getNombreProducto());
            
            if (itemEntity.getProducto().getImagen() != null) {
                String base64Image = Base64.getEncoder().encodeToString(itemEntity.getProducto().getImagen());
                dto.setImagenBase64("data:image/jpeg;base64," + base64Image); 
            }
        }
        
        dto.setCantidad(itemEntity.getCantidad());
        dto.setPrecioUnitario(itemEntity.getPrecioUnitario());
        
        // Calcular subtotal si no viene calculado
        if (itemEntity.getCantidad() != null && itemEntity.getPrecioUnitario() != null) {
            java.math.BigDecimal cantidadBD = new java.math.BigDecimal(itemEntity.getCantidad());
            dto.setSubtotal(itemEntity.getPrecioUnitario().multiply(cantidadBD));
        }
        
        return dto;
    }
}