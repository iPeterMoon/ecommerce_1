package DAO;

import DAO.interfaces.IPedidoDAO;
import entidades.Pedido;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends GenericDAO<Pedido, Long> implements IPedidoDAO {

    public PedidoDAO() {
        super(Pedido.class);
    }

    private final String BASE_FETCH_QUERY
            = "SELECT DISTINCT p FROM Pedido p "
            + "LEFT JOIN FETCH p.usuario u "
            + "LEFT JOIN FETCH p.pago pa "
            + "LEFT JOIN FETCH p.items i "
            + "LEFT JOIN FETCH i.producto prod ";

    @Override
    public Pedido buscarPorIdEspecifico(Long id) {
        EntityManager em = getEntityManager();
        try {
            String jpql = BASE_FETCH_QUERY + "WHERE p.idPedido = :id";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("id", id);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> buscarPorIdPedidoOIdUsuario(String busqueda) {
        EntityManager em = getEntityManager();
        try {
            Long idBusqueda;
            try {
                idBusqueda = Long.parseLong(busqueda);
            } catch (NumberFormatException e) {
                return new ArrayList<>(); 
            }

            String jpql = BASE_FETCH_QUERY
                    + "WHERE p.idPedido = :id OR p.usuario.idUsuario = :id "
                    + "ORDER BY p.idPedido DESC";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("id", idBusqueda);

            return query.getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> buscarPorUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            String jpql = BASE_FETCH_QUERY
                    + "WHERE p.usuario = :usuario ORDER BY p.idPedido DESC";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("usuario", usuario);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Busca todos los pedidos cargando todas las relaciones necesarias para la
     * conversión a DTO.
     *
     * @return Lista de Pedidos completos.
     */
    @Override
    public List<Pedido> buscarTodosLosPedidos() {
        EntityManager em = getEntityManager();
        try {
            String jpql = BASE_FETCH_QUERY + "ORDER BY p.idPedido DESC";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Pedido buscarPorId(Long id) {
     
        return this.buscarPorIdEspecifico(id);
    }
}
