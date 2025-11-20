package DAO;

import entidades.Pago;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PagoDAO extends GenericDAO<Pago, Long> {

    public PagoDAO() {
        super(Pago.class);
    }

    public List<Pago> buscarPorIdPedido(Long idPedido) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pago> query = em.createQuery(
                    "SELECT p FROM Pago p WHERE p.pedido.idPedido = :idPedido", Pago.class);
            query.setParameter("idPedido", idPedido);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarPorPedido(Long idPedido) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Pago p WHERE p.pedido.idPedido = :idPedido")
                    .setParameter("idPedido", idPedido)
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
