package com.mycompany.api.videojuegos;
 // Adjust if you put it in a sub-package

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, 
                       ContainerResponseContext responseContext) throws IOException {
        
        // 1. Allow requests from ANY website (*)
        responseContext.getHeaders().add(
            "Access-Control-Allow-Origin", "*");
            
        // 2. Allow these HTTP headers (needed for JSON)
        responseContext.getHeaders().add(
            "Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization");
            
        // 3. Allow these HTTP methods (GET, POST, PUT, DELETE)
        responseContext.getHeaders().add(
            "Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}