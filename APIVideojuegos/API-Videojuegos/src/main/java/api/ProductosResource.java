/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import DTO.ProductoDTO;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST Web Service
 *
 * @author petermoon
 */
@Path("productos")
@RequestScoped
public class ProductosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductosResource
     */
    public ProductosResource() {
    }

    /**
     * Retrieves representation of an instance of api.ProductosResource
     * @return an instance of DTO.ProductoDTO
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductoDTO> getJson() {
        return null;
        //TODO
    }

    /**
     * PUT method for updating or creating an instance of ProductosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(ProductoDTO content) {
    }
    
    
}
