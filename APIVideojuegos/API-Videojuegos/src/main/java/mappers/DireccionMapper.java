package mappers;

import DTO.DireccionDTO;
import entidades.Direccion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre Direccion (Entidad) y DireccionDTO
 * * @author petermoon
 */
public class DireccionMapper {
    
    /**
     * Convierte una entidad Direccion a DireccionDTO
     * @param direccion entidad de JPA
     * @return DTO con los datos de la dirección
     */
    public static DireccionDTO toDTO(Direccion direccion) {
        if (direccion == null) {
            return null;
        }
        
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setNombre(direccion.getNombre()); // Mapeo del nombre
        dto.setCalle(direccion.getCalle());
        dto.setNumeroExterior(direccion.getNumeroExterior());
        dto.setColonia(direccion.getColonia());
        dto.setCiudad(direccion.getCiudad());
        dto.setEstado(direccion.getEstado());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        
        return dto;
    }
    
    /**
     * Convierte un DireccionDTO a entidad Direccion
     * @param dto DTO con los datos de la dirección
     * @return entidad Direccion (sin usuario asociado)
     */
    public static Direccion toEntity(DireccionDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(dto.getIdDireccion());
        direccion.setNombre(dto.getNombre()); // Mapeo del nombre
        direccion.setCalle(dto.getCalle());
        direccion.setNumeroExterior(dto.getNumeroExterior());
        direccion.setColonia(dto.getColonia());
        direccion.setCiudad(dto.getCiudad());
        direccion.setEstado(dto.getEstado());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        // Nota: El usuario debe ser asignado manualmente después de crear la entidad
        
        return direccion;
    }
    
    /**
     * Actualiza los datos de una entidad Direccion existente con los datos del DTO
     * No actualiza el ID ni el usuario
     * @param direccion entidad a actualizar
     * @param dto DTO con los nuevos datos
     */
    public static void updateEntityFromDTO(Direccion direccion, DireccionDTO dto) {
        if (direccion == null || dto == null) {
            return;
        }
        
        // Actualizar nombre si está presente
        if (dto.getNombre() != null) {
            direccion.setNombre(dto.getNombre());
        }
        
        if (dto.getCalle() != null) {
            direccion.setCalle(dto.getCalle());
        }
        
        if (dto.getNumeroExterior() != null) {
            direccion.setNumeroExterior(dto.getNumeroExterior());
        }
        
        if (dto.getColonia() != null) {
            direccion.setColonia(dto.getColonia());
        }
        
        if (dto.getCiudad() != null) {
            direccion.setCiudad(dto.getCiudad());
        }
        
        if (dto.getEstado() != null) {
            direccion.setEstado(dto.getEstado());
        }
        
        if (dto.getCodigoPostal() != null) {
            direccion.setCodigoPostal(dto.getCodigoPostal());
        }
    }
    
    /**
     * Convierte una lista de entidades Direccion a lista de DTOs
     * @param direcciones lista de entidades
     * @return lista de DTOs
     */
    public static List<DireccionDTO> toDTOList(List<Direccion> direcciones) {
        if (direcciones == null) {
            return new ArrayList<>();
        }
        
        return direcciones.stream()
                .map(DireccionMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Convierte una lista de DTOs a lista de entidades Direccion
     * @param dtos lista de DTOs
     * @return lista de entidades
     */
    public static List<Direccion> toEntityList(List<DireccionDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        
        return dtos.stream()
                .map(DireccionMapper::toEntity)
                .collect(Collectors.toList());
    }
}