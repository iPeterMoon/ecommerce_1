/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author benja
 */
public class ClasificacionDTO {

    private Long idClasificacion;
    private String nombre;

    public ClasificacionDTO(Long idClasificacion, String nombre) {
        this.idClasificacion = idClasificacion;
        this.nombre = nombre;
    }

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public String getNombre() {
        return nombre;
    }
}
