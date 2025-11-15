package DAO;

import DAO.interfaces.IVideojuegoDAO;
import entidades.Videojuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class VideojuegoDAO extends GenericDAO<Videojuego, Long> implements IVideojuegoDAO {

    public VideojuegoDAO() {
        super(Videojuego.class);
    }

    @Override
    public Videojuego buscarPorNombreExacto(String nombre) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.nombre = :nombre", Videojuego.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } 
    }

    @Override
    public List<Videojuego> buscarTodos() {
        EntityManager em = getEntityManager();
        TypedQuery<Videojuego> query = em.createQuery(
                "SELECT DISTINCT v FROM Videojuego v LEFT JOIN FETCH v.categorias", Videojuego.class);
        return query.getResultList();
    }

    @Override
    public Videojuego buscarPorId(Long id) {
        EntityManager em = getEntityManager();

        TypedQuery<Videojuego> query = em.createQuery(
                "SELECT v FROM Videojuego v LEFT JOIN FETCH v.categorias WHERE v.idVideojuego = :id", Videojuego.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
    }
}
