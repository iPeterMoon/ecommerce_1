package DAO.interfaces;

import entidades.Categoria;
import entidades.Plataforma;
import entidades.Producto;
import java.util.List;

public interface IProductoDAO extends IGenericDAO<Producto, Long> {
    
    List<Producto> buscarPorNombre(String nombre);
    
    List<Producto> buscarPorPlataforma(Plataforma plataforma);

    List<Producto> buscarPorCategoria(Categoria categoria);
}