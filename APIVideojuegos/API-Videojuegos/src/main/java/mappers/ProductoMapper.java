package mappers;

import DTO.ProductoDTO;
import entidades.Producto;
import entidades.Videojuego;
import entidades.Plataforma;
import java.util.Base64;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto entity) {
        if (entity == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(entity.getIdProducto());
        dto.setNombreProducto(entity.getNombreProducto());
        dto.setPrecio(entity.getPrecio());
        dto.setStock(entity.getStock() != null ? entity.getStock() : 0);
        dto.setDescripcion(entity.getDescripcion());

        if (entity.getImagen() != null) {
             String base64 = Base64.getEncoder().encodeToString(entity.getImagen());
             dto.setImagenBase64("data:image/png;base64," + base64); // Ajustar mime-type según necesidad
        }

        if (entity.getVideojuego() != null) {
            dto.setIdVideojuego(entity.getVideojuego().getIdVideojuego());
            dto.setNombreVideojuego(entity.getVideojuego().getNombre());
        }

        if (entity.getPlataforma() != null) {
            dto.setIdPlataforma(entity.getPlataforma().getIdPlataforma());
            dto.setNombrePlataforma(entity.getPlataforma().getNombre());
        }

        return dto;
    }

    public static Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;

        Producto entity = new Producto();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombreProducto(dto.getNombreProducto());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setDescripcion(dto.getDescripcion());

        if (dto.getImagenBase64() != null && !dto.getImagenBase64().isEmpty()) {
            try {
                String base64Clean = dto.getImagenBase64();
                if (base64Clean.contains(",")) {
                    base64Clean = base64Clean.split(",")[1];
                }
                entity.setImagen(Base64.getDecoder().decode(base64Clean));
            } catch (IllegalArgumentException e) {
                System.err.println("Error decodificando imagen base64: " + e.getMessage());
            }
        }

        if (dto.getIdVideojuego() != null) {
            Videojuego v = new Videojuego();
            v.setIdVideojuego(dto.getIdVideojuego());
            entity.setVideojuego(v);
        }

        if (dto.getIdPlataforma() != null) {
            Plataforma p = new Plataforma();
            p.setIdPlataforma(dto.getIdPlataforma());
            entity.setPlataforma(p);
        }

        return entity;
    }
}