package modelo;

import DAO.ResenaDAO;
import DAO.interfaces.IResenaDAO;
import DTO.ResenaDTO;
import entidades.Resena;
import java.util.List;
import java.util.stream.Collectors;

public class ResenaBO {

    private IResenaDAO resenaDAO;

    public ResenaBO() {
        this.resenaDAO = new ResenaDAO();
    }

    public List<ResenaDTO> listarResenasPorProducto(Long idProducto) {
        List<Resena> resenas = resenaDAO.buscarPorIdProducto(idProducto);
        
        return resenas.stream()
                      .map(this::convertirADTO) 
                      .collect(Collectors.toList());
    }


    public void eliminarResena(Long idResena) throws Exception {
        Resena resena = resenaDAO.buscarPorId(idResena);
        if (resena == null) {
            throw new Exception("La reseña con ID " + idResena + " no existe.");
        }
        resenaDAO.eliminar(resena);
    }

    public void actualizarResena(ResenaDTO dto) throws Exception {
        Resena resena = resenaDAO.buscarPorId(dto.getIdResena());
        if (resena == null) {
            throw new Exception("La reseña no existe.");
        }
        
        resena.setComentario(dto.getComentario());
        resena.setCalificacion(dto.getCalificacion());
        
        resenaDAO.actualizar(resena);
    }

    public ResenaDTO obtenerResenaPorId(Long idResena) throws Exception {
         Resena resena = resenaDAO.buscarPorId(idResena);
         if (resena == null) {
            throw new Exception("La reseña no existe.");
         }
         return convertirADTO(resena);
    }

    private ResenaDTO convertirADTO(Resena resena) {
        ResenaDTO dto = new ResenaDTO();
        dto.setIdResena(resena.getIdResena());
        dto.setFecha(resena.getFecha());
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());

        if (resena.getProducto() != null) {
            dto.setIdProducto(resena.getProducto().getIdProducto()); 
        }
        
        if (resena.getUsuario() != null) {
            dto.setIdUsuario(resena.getUsuario().getIdUsuario());
            dto.setNombreUsuario(resena.getUsuario().getNombre()); 
        }
        
        return dto;
    }
}