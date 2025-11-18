package DAO.interfaces;

import entidades.Videojuego;
import java.util.List;

public interface IVideojuegoDAO extends IGenericDAO<Videojuego, Long> {

    Videojuego buscarPorNombreExacto(String nombre);

    List<Videojuego> buscarPorNombre(String busqueda);
}
