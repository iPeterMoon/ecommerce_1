package mappers;

import DTO.VideojuegoDTO;
import entidades.Videojuego;
import entidades.Clasificacion;
import entidades.Categoria;
import java.util.HashSet;
import java.util.stream.Collectors;

public class VideojuegoMapper {

    public static VideojuegoDTO toDTO(Videojuego entity) {
        if (entity == null) return null;

        VideojuegoDTO dto = new VideojuegoDTO();
        dto.setIdVideojuego(entity.getIdVideojuego());
        dto.setNombre(entity.getNombre());
        dto.setDesarrollador(entity.getDesarrollador());
        dto.setAnoLanzamiento(entity.getAnoLanzamiento());

        if (entity.getClasificacion() != null) {
            dto.setIdClasificacion(entity.getClasificacion().getIdClasificacion());
            dto.setNombreClasificacion(entity.getClasificacion().getNombre());
        }

        if (entity.getCategorias() != null) {
            dto.setIdsCategorias(entity.getCategorias().stream()
                .map(Categoria::getIdCategoria)
                .collect(Collectors.toSet()));
            
            String nombres = entity.getCategorias().stream()
                .map(Categoria::getNombre)
                .collect(Collectors.joining(", "));
            dto.setNombresCategorias(nombres);
        } else {
            dto.setIdsCategorias(new HashSet<>());
            dto.setNombresCategorias("");
        }

        return dto;
    }

    public static Videojuego toEntity(VideojuegoDTO dto) {
        if (dto == null) return null;

        Videojuego entity = new Videojuego();
        entity.setIdVideojuego(dto.getIdVideojuego());
        entity.setNombre(dto.getNombre());
        entity.setDesarrollador(dto.getDesarrollador());
        entity.setAnoLanzamiento(dto.getAnoLanzamiento());
        
        if (dto.getIdClasificacion() != null) {
            Clasificacion c = new Clasificacion();
            c.setIdClasificacion(dto.getIdClasificacion());
            entity.setClasificacion(c);
        }
        
        return entity;
    }
}