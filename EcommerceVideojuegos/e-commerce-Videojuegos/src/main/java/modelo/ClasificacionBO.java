/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import DAO.ClasificacionDAO;
import DAO.interfaces.IClasificacionDAO;
import DTO.ClasificacionDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author benja
 */
public class ClasificacionBO {

    private IClasificacionDAO clasificacionDAO;

    public ClasificacionBO() {
        this.clasificacionDAO = new ClasificacionDAO();
    }

    public List<ClasificacionDTO> listarTodas() {
        return clasificacionDAO.buscarTodos().stream()
                .map(c -> new ClasificacionDTO(c.getIdClasificacion(), c.getNombre()))
                .collect(Collectors.toList());
    }
}
