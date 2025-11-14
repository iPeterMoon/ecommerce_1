package DAO.interfaces;

import entidades.Usuario;
import java.util.List;

public interface IUsuarioDAO extends IGenericDAO<Usuario, Long> {

    Usuario buscarPorEmail(String email);

    List<Usuario> buscarPorNombre(String nombre);
}
