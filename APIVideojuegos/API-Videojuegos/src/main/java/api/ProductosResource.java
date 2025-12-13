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
        this.producto = new ProductoDAO();
    }

    /**
     * Retrieves representation of an instance of api.ProductosResource
     *
     * @return an instance of DTO.ProductoDTO Obtiene y regresa todos los
     * productos.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductoDTO> getJson() {
        List<Producto> productos = producto.buscarTodos();

        List<ProductoDTO> productosDT = new ArrayList<>();

        for (Producto p : productos) {
            ProductoDTO dto = new ProductoDTO();

            dto.setIdProducto(p.getIdProducto());
            dto.setNombreProducto(p.getNombreProducto());
            dto.setPrecio(p.getPrecio());
            dto.setDescripcion(p.getDescripcion());

            if (p.getStock() != null) {
                dto.setStock(p.getStock());
            } else {
                dto.setStock(0);
            }

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

        return productosDT.stream()
                .limit(16)
                .collect(Collectors.toList());
    }

    /**
     * PUT method for updating or creating an instance of ProductosResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(ProductoDTO content
    ) {
    }

}
