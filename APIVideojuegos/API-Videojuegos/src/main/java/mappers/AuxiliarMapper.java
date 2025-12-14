package mappers;

import DTO.PlataformaDTO;
import DTO.ClasificacionDTO;
import entidades.Plataforma;
import entidades.Clasificacion;

public class AuxiliarMapper {

    // --- PLATAFORMA ---
    public static PlataformaDTO toPlataformaDTO(Plataforma entity) {
        if (entity == null) return null;
        return new PlataformaDTO(entity.getIdPlataforma(), entity.getNombre());
    }

    public static Plataforma toPlataformaEntity(PlataformaDTO dto) {
        if (dto == null) return null;
        Plataforma entity = new Plataforma();
        entity.setIdPlataforma(dto.getIdPlataforma());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    // --- CLASIFICACION ---
    public static ClasificacionDTO toClasificacionDTO(Clasificacion entity) {
        if (entity == null) return null;
        return new ClasificacionDTO(entity.getIdClasificacion(), entity.getNombre());
    }

    public static Clasificacion toClasificacionEntity(ClasificacionDTO dto) {
        if (dto == null) return null;
        Clasificacion entity = new Clasificacion();
        entity.setIdClasificacion(dto.getIdClasificacion());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}