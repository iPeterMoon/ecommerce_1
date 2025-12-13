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
import java.util.stream.Stream; // Import Stream

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

    public ProductosResource() {
        this.producto = new ProductoDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductoDTO> getJson(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("q") String query // <--- NEW PARAMETER for search
    ) {

        System.out.println("API REACHED! Query value is: " + query);
        // 1. Get all products (Raw Data)
        List<Producto> productos = producto.buscarTodos();
        List<ProductoDTO> productosDT = new ArrayList<>();

        // 2. Map Entities to DTOs
        for (Producto p : productos) {
            ProductoDTO dto = new ProductoDTO();

            dto.setIdProducto(p.getIdProducto());
            dto.setNombreProducto(p.getNombreProducto());
            dto.setPrecio(p.getPrecio());
            dto.setDescripcion(p.getDescripcion());
            dto.setStock(p.getStock() != null ? p.getStock() : 0);

            if (p.getImagen() != null) {
                String imagenBase64 = java.util.Base64.getEncoder().encodeToString(p.getImagen());
                dto.setImagenBase64("data:image/jpeg;base64," + imagenBase64);
            }

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

        // 3. Create a Stream
        Stream<ProductoDTO> stream = productosDT.stream();

        // 4. APPLY SEARCH FILTER (If query is not null or empty)
        if (query != null && !query.trim().isEmpty()) {
            String lowerQuery = query.toLowerCase(); // Case insensitive logic

            stream = stream.filter(p
                    -> // Search by Product Name
                    (p.getNombreProducto() != null && p.getNombreProducto().toLowerCase().contains(lowerQuery))
                    || // Search by Platform Name (Category)
                    (p.getNombrePlataforma() != null && p.getNombrePlataforma().toLowerCase().contains(lowerQuery))
                    || // Search by Video Game Name
                    (p.getNombreVideojuego() != null && p.getNombreVideojuego().toLowerCase().contains(lowerQuery))
                    || // Optional: Search by Description
                    (p.getDescripcion() != null && p.getDescripcion().toLowerCase().contains(lowerQuery))
            );
        }

        // 5. Apply Pagination
        int skip = (page - 1) * size;

        return stream
                .skip(skip)
                .limit(size)
                .collect(Collectors.toList());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(ProductoDTO content) {
    }
}
