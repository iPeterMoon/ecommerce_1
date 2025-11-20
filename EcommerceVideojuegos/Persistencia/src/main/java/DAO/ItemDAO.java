package DAO;

import entidades.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ItemDAO extends GenericDAO<Item, Long> {

    public ItemDAO() {
        super(Item.class);
    }

    public List<Item> buscarPorIdPedido(Long idPedido) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Item> query = em.createQuery(
                    "SELECT i FROM Item i WHERE i.pedido.idPedido = :idPedido", Item.class);
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
            em.createQuery("DELETE FROM Item i WHERE i.pedido.idPedido = :idPedido")
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
