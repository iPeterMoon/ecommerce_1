/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;

/**
 *
 * @author benja
 */
public class ProductoDTO {

    private Long idProducto;
    private String nombreProducto;
    private String imagenUrl;
    private BigDecimal precio;
    private String descripcion;
    private Integer stock; 
    private String plataformaNombre;
    private String videojuegoNombre;
    private String desarrollador;
    private String anoLanzamiento;
    private String clasificacionNombre;

    public ProductoDTO() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPlataformaNombre() {
        return plataformaNombre;
    }

    public void setPlataformaNombre(String plataformaNombre) {
        this.plataformaNombre = plataformaNombre;
    }

    public String getVideojuegoNombre() {
        return videojuegoNombre;
    }

    public void setVideojuegoNombre(String videojuegoNombre) {
        this.videojuegoNombre = videojuegoNombre;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(String anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public String getClasificacionNombre() {
        return clasificacionNombre;
    }

    public void setClasificacionNombre(String clasificacionNombre) {
        this.clasificacionNombre = clasificacionNombre;
    }
}