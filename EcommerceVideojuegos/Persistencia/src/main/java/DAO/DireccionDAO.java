package DAO;

import entidades.Direccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DireccionDAO extends GenericDAO<Direccion, Long> {

    public DireccionDAO() {
        super(Direccion.class);
    }

    public List<Direccion> buscarPorIdUsuario(Long idUsuario) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Direccion> query = em.createQuery(
                    "SELECT d FROM Direccion d WHERE d.usuario.idUsuario = :idUsuario", Direccion.class);
            query.setParameter("idUsuario", idUsuario);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarPorUsuario(Long idUsuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Direccion d WHERE d.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
