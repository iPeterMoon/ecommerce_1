/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import DAO.PlataformaDAO;
import DAO.interfaces.IPlataformaDAO;
import DTO.PlataformaDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author benja
 */
public class PlataformaBO {

    private IPlataformaDAO plataformaDAO;

    public PlataformaBO() {
        this.plataformaDAO = new PlataformaDAO();
    }

    public List<PlataformaDTO> listarTodas() {
        return plataformaDAO.buscarTodos().stream()
                .map(p -> new PlataformaDTO(p.getIdPlataforma(), p.getNombre()))
                .collect(Collectors.toList());
    }
}
