package modelo;


import DAO.ProductoDAO;
import DAO.interfaces.IProductoDAO;
import DTO.ProductoDTO;
import entidades.Producto;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoBO {

    private IProductoDAO productoDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAO();
    }

    private ProductoDTO convertirADTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(p.getIdProducto());
        dto.setNombreProducto(p.getNombreProducto());
        dto.setImagenUrl(p.getImagenUrl());
        dto.setPrecio(p.getPrecio()); 
        dto.setStock(p.getStock());
        dto.setDescripcion(p.getDescripcion());
        if (p.getPlataforma() != null) {
            dto.setIdPlataforma(p.getPlataforma().getIdPlataforma());
        }
        if (p.getVideojuego() != null) {
            dto.setIdVideojuego(p.getVideojuego().getIdVideojuego());
        }
        return dto;
    }

    public List<ProductoDTO> listarTodosLosProductos() {
        List<Producto> productos = productoDAO.buscarTodos();
        return productos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
}