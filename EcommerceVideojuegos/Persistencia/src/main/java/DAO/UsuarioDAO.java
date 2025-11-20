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

    @Override
    public Usuario alternarActividad(Long idUsuario) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            em.getTransaction().begin();
            usuario = em.find(Usuario.class, idUsuario);
            if (usuario != null) {
                usuario.setCuentaActiva(!usuario.getCuentaActiva());
                em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usuario;
    }

    public void eliminarConCascada(Long idUsuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Eliminar pagos de los pedidos del usuario
            em.createQuery(
                    "DELETE FROM Pago p WHERE p.pedido.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            // 2. Eliminar items de los pedidos del usuario
            em.createQuery(
                    "DELETE FROM Item i WHERE i.pedido.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            // 3. Eliminar pedidos del usuario
            em.createQuery(
                    "DELETE FROM Pedido p WHERE p.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            // 4. Eliminar reseñas del usuario
            em.createQuery(
                    "DELETE FROM Resena r WHERE r.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            // 5. Eliminar direcciones del usuario
            em.createQuery(
                    "DELETE FROM Direccion d WHERE d.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            // 6. Eliminar el usuario (esto libera la referencia al carrito)
            em.createQuery(
                    "DELETE FROM Usuario u WHERE u.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            // 7. Finalmente, eliminar carrito del usuario
            em.createQuery(
                    "DELETE FROM CarritoCompra c WHERE c.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar usuario en cascada: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
