package DAO;

import DAO.interfaces.IUsuarioDAO;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import util.Security;

import java.util.List;

// Hereda la implementación de GenericDAOImpl
public class UsuarioDAO extends GenericDAO<Usuario, Long> implements IUsuarioDAO {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :email", Usuario.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre", Usuario.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario buscarPorCredenciales(String email, String password){
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :email AND u.contrasena = :password", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("password", Security.hashear(password));
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }        
    } 
}
