package mappers;

import DTO.DireccionDTO;
import DTO.UsuarioDTO;
import entidades.Direccion;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre Usuario (Entidad) y UsuarioDTO
 * 
 * @author peter
 */
public class UsuarioMapper {
    
    /**
     * Convierte una entidad Usuario a UsuarioDTO
     * @param usuario entidad de JPA
     * @return DTO con los datos del usuario
     */
    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol() != null ? usuario.getRol().name() : null);
        dto.setCuentaActiva(usuario.getCuentaActiva());
        
        // Convertir direcciones si existen
        if (usuario.getDirecciones() != null && !usuario.getDirecciones().isEmpty()) {
            List<DireccionDTO> direccionesDTO = usuario.getDirecciones().stream()
                    .map(DireccionMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setDirecciones(direccionesDTO);
        }
        
        return dto;
    }
    
    /**
     * Convierte una entidad Usuario a UsuarioDTO (versión simple sin relaciones)
     * Útil para evitar lazy loading de direcciones
     * @param usuario entidad de JPA
     * @return DTO con los datos básicos del usuario
     */
    public static UsuarioDTO toDTOSimple(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol() != null ? usuario.getRol().name() : null);
        dto.setCuentaActiva(usuario.getCuentaActiva());
        
        return dto;
    }
    
    /**
     * Convierte un UsuarioDTO a entidad Usuario
     * @param dto DTO con los datos del usuario
     * @return entidad Usuario
     */
    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setCuentaActiva(dto.isCuentaActiva());
        
        // Convertir el rol de String a Enum
        if (dto.getRol() != null) {
            try {
                usuario.setRol(enums.RolUsuario.valueOf(dto.getRol()));
            } catch (IllegalArgumentException e) {
                // Si el rol no es válido, asignar uno por defecto
                usuario.setRol(enums.RolUsuario.CLIENTE);
            }
        }
        
        // Convertir direcciones si existen
        if (dto.getDirecciones() != null && !dto.getDirecciones().isEmpty()) {
            List<Direccion> direcciones = dto.getDirecciones().stream()
                    .map(DireccionMapper::toEntity)
                    .collect(Collectors.toList());
            usuario.setDirecciones(direcciones);
        }
        
        return usuario;
    }
    
    /**
     * Actualiza los datos de una entidad Usuario existente con los datos del DTO
     * No actualiza el ID, la contraseña ni las relaciones
     * @param usuario entidad a actualizar
     * @param dto DTO con los nuevos datos
     */
    public static void updateEntityFromDTO(Usuario usuario, UsuarioDTO dto) {
        if (usuario == null || dto == null) {
            return;
        }
        
        if (dto.getNombre() != null) {
            usuario.setNombre(dto.getNombre());
        }
        
        if (dto.getCorreo() != null) {
            usuario.setCorreo(dto.getCorreo());
        }
        
        if (dto.getTelefono() != null) {
            usuario.setTelefono(dto.getTelefono());
        }
        
        usuario.setCuentaActiva(dto.isCuentaActiva());
        
        if (dto.getRol() != null) {
            try {
                usuario.setRol(enums.RolUsuario.valueOf(dto.getRol()));
            } catch (IllegalArgumentException e) {
                // Mantener el rol actual si no es válido
            }
        }
    }
    
    /**
     * Convierte una lista de entidades Usuario a lista de DTOs
     * @param usuarios lista de entidades
     * @return lista de DTOs
     */
    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        if (usuarios == null) {
            return new ArrayList<>();
        }
        
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Convierte una lista de entidades Usuario a lista de DTOs simples
     * @param usuarios lista de entidades
     * @return lista de DTOs simples
     */
    public static List<UsuarioDTO> toDTOSimpleList(List<Usuario> usuarios) {
        if (usuarios == null) {
            return new ArrayList<>();
        }
        
        return usuarios.stream()
                .map(UsuarioMapper::toDTOSimple)
                .collect(Collectors.toList());
    }
}