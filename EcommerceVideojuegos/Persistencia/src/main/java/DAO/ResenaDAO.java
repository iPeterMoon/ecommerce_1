package DAO;

import DAO.interfaces.IResenaDAO;
import entidades.Producto;
import entidades.Resena;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ResenaDAO extends GenericDAO<Resena, Long> implements IResenaDAO {

    public ResenaDAO() {
        super(Resena.class);
    }

    @Override
    public List<Resena> buscarPorProducto(Producto producto) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.producto = :producto", Resena.class);
            query.setParameter("producto", producto);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Resena> buscarPorUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.usuario = :usuario", Resena.class);
            query.setParameter("usuario", usuario);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
