package DAO.interfaces;

import entidades.Producto;
import entidades.Resena;
import entidades.Usuario;
import java.util.List;

public interface IResenaDAO extends IGenericDAO<Resena, Long> {
    
    List<Resena> buscarPorProducto(Producto producto);
    
    List<Resena> buscarPorUsuario(Usuario usuario);
    
    List<Resena> buscarPorIdProducto(Long idProducto);
}