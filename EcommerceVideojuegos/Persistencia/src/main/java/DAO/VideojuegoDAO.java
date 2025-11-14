package DAO;

import DAO.interfaces.IVideojuegoDAO;
import entidades.Videojuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

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
        } finally {
            em.close();
        }
    }
}
