package DAO;

import DAO.interfaces.IPedidoDAO;
import entidades.Pedido;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PedidoDAO extends GenericDAO<Pedido, Long> implements IPedidoDAO {

    public PedidoDAO() {
        super(Pedido.class);
    }

    @Override
    public List<Pedido> buscarPorUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.usuario = :usuario ORDER BY p.idPedido DESC", Pedido.class);
            query.setParameter("usuario", usuario);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Pedido> buscarTodosLosPedidos() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM Pedido p "
                    + "JOIN FETCH p.usuario "
                    + "JOIN FETCH p.pago "
                    + "LEFT JOIN FETCH p.items"; 

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
