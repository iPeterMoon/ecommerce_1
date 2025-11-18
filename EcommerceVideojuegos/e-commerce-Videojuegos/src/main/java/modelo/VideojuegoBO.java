/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import DAO.CategoriaDAO;
import DAO.ClasificacionDAO;
import DAO.ProductoDAO;
import DAO.VideojuegoDAO;
import DAO.interfaces.ICategoriaDAO;
import DAO.interfaces.IClasificacionDAO;
import DAO.interfaces.IProductoDAO;
import DAO.interfaces.IVideojuegoDAO;
import DTO.VideojuegoDTO;
import entidades.Categoria;
import entidades.Clasificacion;
import entidades.Videojuego;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author benja
 */
public class VideojuegoBO {

    private IVideojuegoDAO videojuegoDAO;
    private IClasificacionDAO clasificacionDAO;
    private ICategoriaDAO categoriaDAO;
    private IProductoDAO productoDAO;

    public VideojuegoBO() {
        this.videojuegoDAO = new VideojuegoDAO();
        this.clasificacionDAO = new ClasificacionDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.productoDAO = new ProductoDAO();
    }

    public List<VideojuegoDTO> listarTodosLosVideojuegos() {
        List<Videojuego> listaEntidades = videojuegoDAO.buscarTodos();
        return listaEntidades.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    public List<VideojuegoDTO> buscarPorNombre(String nombre) throws Exception {
        List<Videojuego> listaVideojuegos = videojuegoDAO.buscarPorNombre(nombre);
        return (listaVideojuegos != null) ? listaEntidadAListaDTO(listaVideojuegos) : null;
    }

    public VideojuegoDTO obtenerVideojuegoParaEditar(Long id) {
        Videojuego entidad = videojuegoDAO.buscarPorId(id);
        return (entidad != null) ? convertirEntidadADTO(entidad) : null;
    }

    public void crearVideojuego(VideojuegoDTO dto) throws Exception {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio.");
        }

        Videojuego entidadExistente = videojuegoDAO.buscarPorNombreExacto(dto.getNombre());
        if (entidadExistente != null) {
            throw new Exception("Ya existe un videojuego con ese nombre.");
        }

        Videojuego entidad = convertirDTOAEntidad(dto, new Videojuego());
        videojuegoDAO.crear(entidad);
    }

    public void actualizarVideojuego(VideojuegoDTO dto) throws Exception {
        if (dto.getIdVideojuego() == null) {
            throw new Exception("ID de videojuego no válido.");
        }

        Videojuego entidad = videojuegoDAO.buscarPorId(dto.getIdVideojuego());
        if (entidad == null) {
            throw new Exception("No se encontró el videojuego para actualizar.");
        }

        Videojuego entidadExistente = videojuegoDAO.buscarPorNombreExacto(dto.getNombre());
        if (entidadExistente != null && !entidadExistente.getIdVideojuego().equals(dto.getIdVideojuego())) {
            throw new Exception("Ya existe OTRO videojuego con ese nombre.");
        }

        Videojuego entidadActualizada = convertirDTOAEntidad(dto, entidad);
        videojuegoDAO.actualizar(entidadActualizada);
    }

    public void eliminarVideojuego(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID no puede ser nulo.");
        }
        Videojuego entidad = videojuegoDAO.buscarPorId(id);
        if (entidad != null) {
            videojuegoDAO.eliminar(entidad);
        } else {
            throw new Exception("No se encontró el videojuego para eliminar.");
        }
    }

    private VideojuegoDTO convertirEntidadADTO(Videojuego v) {
        VideojuegoDTO dto = new VideojuegoDTO();
        dto.setIdVideojuego(v.getIdVideojuego());
        dto.setNombre(v.getNombre());
        dto.setDesarrollador(v.getDesarrollador());
        dto.setAnoLanzamiento(v.getAnoLanzamiento());

        if (v.getClasificacion() != null) {
            dto.setIdClasificacion(v.getClasificacion().getIdClasificacion());
            dto.setNombreClasificacion(v.getClasificacion().getNombre());
        }

        if (v.getCategorias() != null && !v.getCategorias().isEmpty()) {
            Set<Long> ids = v.getCategorias().stream()
                    .map(Categoria::getIdCategoria)
                    .collect(Collectors.toSet());
            dto.setIdsCategorias(ids);

            String nombres = v.getCategorias().stream()
                    .map(Categoria::getNombre)
                    .collect(Collectors.joining(", "));
            dto.setNombresCategorias(nombres);
        }
        return dto;
    }

    private List<VideojuegoDTO> listaEntidadAListaDTO(List<Videojuego> listaEntidades) {
        if (listaEntidades == null) {
            return new java.util.ArrayList<>(); 
        }
        return listaEntidades.stream()
                .map(this::convertirEntidadADTO) 
                .collect(Collectors.toList());
    }

    private Videojuego convertirDTOAEntidad(VideojuegoDTO dto, Videojuego entidad) {
        entidad.setNombre(dto.getNombre());
        entidad.setDesarrollador(dto.getDesarrollador());
        entidad.setAnoLanzamiento(dto.getAnoLanzamiento());

        if (dto.getIdClasificacion() != null) {
            Clasificacion clasificacion = clasificacionDAO.buscarPorId(dto.getIdClasificacion());
            entidad.setClasificacion(clasificacion);
        } else {
            entidad.setClasificacion(null);
        }
        if (dto.getIdsCategorias() != null && !dto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categorias = dto.getIdsCategorias().stream()
                    .map(id -> categoriaDAO.buscarPorId(id))
                    .filter(c -> c != null)
                    .collect(Collectors.toSet());
            entidad.setCategorias(categorias);
        } else {
            if (entidad.getCategorias() != null) {
                entidad.getCategorias().clear();
            } else {
                entidad.setCategorias(new java.util.HashSet<>());
            }
        }

        return entidad;
    }
}
