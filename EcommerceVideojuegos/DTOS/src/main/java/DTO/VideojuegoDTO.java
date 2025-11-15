/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Set;

/**
 *
 * @author benja
 */
public class VideojuegoDTO {

    private Long idVideojuego;
    private String nombre;
    private String desarrollador;
    private String anoLanzamiento;
    private Long idClasificacion;
    private String nombreClasificacion;
    private Set<Long> idsCategorias;
    private String nombresCategorias;

    public VideojuegoDTO() {
    }

    public Long getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Long idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Long idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public Set<Long> getIdsCategorias() {
        return idsCategorias;
    }

    public void setIdsCategorias(Set<Long> idsCategorias) {
        this.idsCategorias = idsCategorias;
    }

    public String getNombresCategorias() {
        return nombresCategorias;
    }

    public void setNombresCategorias(String nombresCategorias) {
        this.nombresCategorias = nombresCategorias;
    }
}
