/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.interfaces.ICategoriaDAO;
import entidades.Categoria;

/**
 *
 * @author benja
 */
public class CategoriaDAO extends GenericDAO<Categoria, Long> implements ICategoriaDAO {
    
    public CategoriaDAO() {
        super(Categoria.class);
    }
    
}