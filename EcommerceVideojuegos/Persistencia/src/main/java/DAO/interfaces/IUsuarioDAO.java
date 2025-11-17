package DAO.interfaces;

import entidades.Usuario;
import java.util.List;

public interface IUsuarioDAO extends IGenericDAO<Usuario, Long> {

    Usuario buscarPorEmail(String email);

    Usuario buscarPorCredenciales(String email, String password);

    List<Usuario> buscarPorNombre(String nombre);

}
