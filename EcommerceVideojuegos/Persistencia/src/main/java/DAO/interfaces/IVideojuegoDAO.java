package DAO.interfaces;

import entidades.Videojuego;

public interface IVideojuegoDAO extends IGenericDAO<Videojuego, Long> {

    Videojuego buscarPorNombreExacto(String nombre);

}
