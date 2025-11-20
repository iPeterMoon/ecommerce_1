package modelo;

import DAO.PlataformaDAO;
import DAO.ProductoDAO;
import DAO.VideojuegoDAO;
import DAO.interfaces.IPlataformaDAO;
import DAO.interfaces.IProductoDAO;
import DAO.interfaces.IVideojuegoDAO;
import DTO.ProductoDTO;
import entidades.Plataforma;
import entidades.Producto;
import entidades.Videojuego;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author benja
 */
public class ProductoBO {

    private IProductoDAO productoDAO;
    private IPlataformaDAO plataformaDAO;
    private IVideojuegoDAO videojuegoDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAO();
        this.plataformaDAO = new PlataformaDAO();
        this.videojuegoDAO = new VideojuegoDAO();
    }

    private ProductoDTO convertirADTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(p.getIdProducto());
        dto.setNombreProducto(p.getNombreProducto());
        
        if (p.getImagen() != null) {
            dto.setImagenBase64(Base64.getEncoder().encodeToString(p.getImagen()));
        }
        
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        dto.setDescripcion(p.getDescripcion());
        if (p.getPlataforma() != null) {
            dto.setIdPlataforma(p.getPlataforma().getIdPlataforma());
            dto.setNombrePlataforma(p.getPlataforma().getNombre());
        }
        if (p.getVideojuego() != null) {
            dto.setIdVideojuego(p.getVideojuego().getIdVideojuego());
            dto.setNombreVideojuego(p.getVideojuego().getNombre());
        }
        return dto;
    }

    public List<ProductoDTO> listarTodosLosProductos() {
        List<Producto> productos = productoDAO.buscarTodos();
        return productos.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<ProductoDTO> buscarPorNombre(String nombre) throws Exception {
        List<Producto> lista = productoDAO.buscarPorNombre(nombre);
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public ProductoDTO obtenerProductoParaEditar(Long id) {
        Producto p = productoDAO.buscarPorId(id);
        return (p != null) ? convertirADTO(p) : null;
    }

    public ProductoDTO crearProducto(ProductoDTO dto) throws Exception {
        if (dto.getIdVideojuego() == null || dto.getIdPlataforma() == null) {
            throw new Exception("Videojuego y Plataforma son obligatorios.");
        }
        Videojuego vj = videojuegoDAO.buscarPorId(dto.getIdVideojuego());
        Plataforma plat = plataformaDAO.buscarPorId(dto.getIdPlataforma());
        if (vj == null || plat == null) {
            throw new Exception("Videojuego o Plataforma no encontrados.");
        }

        Producto p = new Producto();
        p.setNombreProducto(dto.getNombreProducto());
        p.setStock(dto.getStock());
        p.setPrecio(dto.getPrecio());
        
        if (dto.getImagenBase64() != null) {
            p.setImagen(Base64.getDecoder().decode(dto.getImagenBase64()));
        }
        
        p.setDescripcion(dto.getDescripcion());
        p.setVideojuego(vj);
        p.setPlataforma(plat);

        productoDAO.crear(p);
        return convertirADTO(p);
    }

    public ProductoDTO actualizarProducto(ProductoDTO dto) throws Exception {
        if (dto.getIdProducto() == null) {
            throw new Exception("ID de producto no válido.");
        }

        Producto p = productoDAO.buscarPorId(dto.getIdProducto());
        if (p == null) {
            throw new Exception("Producto no encontrado.");
        }

        Plataforma plat = plataformaDAO.buscarPorId(dto.getIdPlataforma());
        if (plat == null) {
            throw new Exception("Plataforma no válida.");
        }

        p.setNombreProducto(dto.getNombreProducto());
        p.setStock(dto.getStock());
        p.setPrecio(dto.getPrecio());
        
        if (dto.getImagenBase64() != null) {
            p.setImagen(Base64.getDecoder().decode(dto.getImagenBase64()));
        }
        
        p.setDescripcion(dto.getDescripcion());
        p.setPlataforma(plat);

        productoDAO.actualizar(p);
        return convertirADTO(p);
    }

    public void eliminarProducto(Long id) throws Exception {
        Producto p = productoDAO.buscarPorId(id);
        if (p == null) {
            throw new Exception("Producto no encontrado.");
        }
        try {
            productoDAO.eliminar(p);
        } catch (Exception e) {
            throw new Exception("No se pudo eliminar el producto (posiblemente está en pedidos).");
        }
    }
}