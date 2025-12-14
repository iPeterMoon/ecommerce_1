package mappers;

import DTO.PagoDTO;
import entidades.Pago;
import enums.EstadoPago;
import enums.MetodoPago;
import java.time.ZoneId;
import java.util.Date;

public class PagoMapper {

    public static PagoDTO toDTO(Pago entity) {
        if (entity == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();
        dto.setIdPago(entity.getIdPago());
        dto.setMonto(entity.getMonto());
        dto.setReferencia(entity.getReferencia());
        
        // Conversión: LocalDateTime -> java.util.Date
        if (entity.getFechaHora() != null) {
            dto.setFechaHora(Date.from(entity.getFechaHora().atZone(ZoneId.systemDefault()).toInstant()));
        }
        
        if (entity.getMetodoPago() != null) {
            dto.setMetodoPago(entity.getMetodoPago().name());
        }
        
        if (entity.getEstadoPago() != null) {
            dto.setEstadoPago(entity.getEstadoPago().name());
        }

        return dto;
    }
    
    public static Pago toEntity(PagoDTO dto) {
        if (dto == null) return null;
        
        Pago entity = new Pago();
        entity.setIdPago(dto.getIdPago());
        entity.setMonto(dto.getMonto());
        entity.setReferencia(dto.getReferencia());
        
        // Conversión: java.util.Date -> LocalDateTime
        if (dto.getFechaHora() != null) {
            entity.setFechaHora(dto.getFechaHora().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
        }
        
        if (dto.getMetodoPago() != null) {
            try {
                entity.setMetodoPago(MetodoPago.valueOf(dto.getMetodoPago()));
            } catch (IllegalArgumentException e) { }
        }
        
        if (dto.getEstadoPago() != null) {
            try {
                entity.setEstadoPago(EstadoPago.valueOf(dto.getEstadoPago()));
            } catch (IllegalArgumentException e) { }
        }
        
        return entity;
    }
}