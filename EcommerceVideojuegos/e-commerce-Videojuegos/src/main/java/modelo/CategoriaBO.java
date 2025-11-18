/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import DAO.CategoriaDAO;
import DAO.interfaces.ICategoriaDAO;
import DTO.CategoriaDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author benja
 */
public class CategoriaBO {

    private ICategoriaDAO categoriaDAO;

    public CategoriaBO() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public List<CategoriaDTO> listarTodas() {
        return categoriaDAO.buscarTodos().stream()
                .map(c -> new CategoriaDTO(c.getIdCategoria(), c.getNombre()))
                .collect(Collectors.toList());
    }
}