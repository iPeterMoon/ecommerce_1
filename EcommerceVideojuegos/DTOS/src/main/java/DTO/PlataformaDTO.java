/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author benja
 */
public class PlataformaDTO {

    private Long idPlataforma;
    private String nombre;

    public PlataformaDTO(Long id, String nombre) {
        this.idPlataforma = id;
        this.nombre = nombre;
    }

    public Long getIdPlataforma() {
        return idPlataforma;
    }

    public String getNombre() {
        return nombre;
    }
}
