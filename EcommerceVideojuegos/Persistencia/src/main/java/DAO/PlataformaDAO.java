/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.interfaces.IPlataformaDAO;
import entidades.Plataforma;

/**
 *
 * @author benja
 */
public class PlataformaDAO extends GenericDAO<Plataforma, Long> implements IPlataformaDAO {

    public PlataformaDAO() {
        super(Plataforma.class);
    }
}
