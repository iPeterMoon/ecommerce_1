/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import DAO.ProductoDAO;
import DTO.ProductoDTO;
import entidades.Producto;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Web Service
 *
 * @author petermoon
 */
@Path("productos")
@RequestScoped
public class ProductosResource {

    ProductoDAO producto;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductosResource
     */
    public ProductosResource() {
        // Recuerda: Si tu DAO no tiene el constructor vacío configurado con Persistence,
        // esto podría fallar luego. Asegúrate de que ProductoDAO instancie su EntityManagerFactory.
        this.producto = new ProductoDAO();
    }

    /**
     * ESTE ES EL ÚNICO MÉTODO GET QUE DEBES TENER.
     * Borra cualquier otro método @GET en esta clase.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductoDTO> getJson(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        // 1. Obtener todos los productos
        List<Producto> productos = producto.buscarTodos();
        List<ProductoDTO> productosDT = new ArrayList<>();

        for (Producto p : productos) {
            ProductoDTO dto = new ProductoDTO();

            // --- Mapeo Completo ---
            dto.setIdProducto(p.getIdProducto());
            dto.setNombreProducto(p.getNombreProducto());
            dto.setPrecio(p.getPrecio());
            dto.setDescripcion(p.getDescripcion());

            // Stock seguro
            dto.setStock(p.getStock() != null ? p.getStock() : 0);

            // Conversión de Imagen
            if (p.getImagen() != null) {
                String imagenBase64 = java.util.Base64.getEncoder().encodeToString(p.getImagen());
                dto.setImagenBase64("data:image/jpeg;base64," + imagenBase64);
            }

            // Relaciones
            if (p.getPlataforma() != null) {
                dto.setIdPlataforma(p.getPlataforma().getIdPlataforma());
                dto.setNombrePlataforma(p.getPlataforma().getNombre());
            }

            if (p.getVideojuego() != null) {
                dto.setIdVideojuego(p.getVideojuego().getIdVideojuego());
                dto.setNombreVideojuego(p.getVideojuego().getNombre());
            }

            productosDT.add(dto);
        }

        int skip = (page - 1) * size;

        if (skip >= productosDT.size()) {
            return new ArrayList<>();
        }

        return productosDT.stream()
                .skip(skip)
                .limit(size)
                .collect(Collectors.toList());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(ProductoDTO content) {
    }
}