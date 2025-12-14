/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import DAO.ProductoDAO;
import DTO.ProductoDTO;
import entidades.Producto;
import entidades.Resena;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            @QueryParam("size") @DefaultValue("12") int size,
            @QueryParam("q") String query,
            @QueryParam("platform") String platformStr,
            @QueryParam("genre") String genreStr,
            @QueryParam("year") Integer year,
            @QueryParam("rating") Integer ratingStr
    ) {

        List<Producto> allProductos = producto.buscarTodos();
        Stream<Producto> stream = allProductos.stream();

        if (query != null && !query.trim().isEmpty()) {
            String lowerQuery = query.toLowerCase();
            stream = stream.filter(p
                    -> (p.getNombreProducto() != null && p.getNombreProducto().toLowerCase().contains(lowerQuery))
                    || (p.getDescripcion() != null && p.getDescripcion().toLowerCase().contains(lowerQuery))
            );
        }

        if (platformStr != null && !platformStr.isEmpty()) {
            List<String> selectedPlatforms = Arrays.stream(platformStr.split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            stream = stream.filter(p
                    -> p.getPlataforma() != null
                    && selectedPlatforms.contains(p.getPlataforma().getNombre().toLowerCase())
            );
        }

        if (genreStr != null && !genreStr.isEmpty()) {
            List<String> selectedGenres = Arrays.stream(genreStr.split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            stream = stream.filter(p -> {
                if (p.getVideojuego() == null || p.getVideojuego().getCategorias() == null) {
                    return false;
                }

                return p.getVideojuego().getCategorias().stream()
                        .anyMatch(cat -> selectedGenres.contains(cat.getNombre().toLowerCase()));
            });
        }

        if (year != null) {
            String yearString = String.valueOf(year); 
            stream = stream.filter(p
                    -> p.getVideojuego() != null
                    && p.getVideojuego().getAnoLanzamiento() != null
                    && 
                    p.getVideojuego().getAnoLanzamiento().equals(yearString)
            );
        }

        if (ratingStr != null) {
            stream = stream.filter(p -> {
                List<Resena> reviews = p.getResenas();
                if (reviews == null || reviews.isEmpty()) {
                    return false;
                }

                double average = reviews.stream()
                        .mapToInt(Resena::getCalificacion) 
                        .average()
                        .orElse(0.0);

                return average >= ratingStr;
            });
        }

        List<Producto> filteredList = stream.collect(Collectors.toList());
        int totalItems = filteredList.size();
        int fromIndex = (page - 1) * size;

        if (fromIndex >= totalItems) {
            return new ArrayList<>();
        }

        int toIndex = Math.min(fromIndex + size, totalItems);
        List<Producto> paginatedList = filteredList.subList(fromIndex, toIndex);

        List<ProductoDTO> dtos = new ArrayList<>();
        for (Producto p : paginatedList) {
            ProductoDTO dto = new ProductoDTO();
            dto.setIdProducto(p.getIdProducto());
            dto.setNombreProducto(p.getNombreProducto());
            dto.setPrecio(p.getPrecio());
            dto.setDescripcion(p.getDescripcion());
            dto.setStock(p.getStock() != null ? p.getStock() : 0);

            if (p.getImagen() != null) {
                String imgBase64 = java.util.Base64.getEncoder().encodeToString(p.getImagen());
                dto.setImagenBase64("data:image/jpeg;base64," + imgBase64);
            }
            if (p.getPlataforma() != null) {
                dto.setIdPlataforma(p.getPlataforma().getIdPlataforma());
                dto.setNombrePlataforma(p.getPlataforma().getNombre());
            }
            if (p.getVideojuego() != null) {
                dto.setIdVideojuego(p.getVideojuego().getIdVideojuego());
                dto.setNombreVideojuego(p.getVideojuego().getNombre());
            }
            dtos.add(dto);
        }

        return dtos;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(ProductoDTO content) {
    }
}
