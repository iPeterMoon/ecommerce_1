package DAO;

import DAO.interfaces.IProductoDAO;
import entidades.Categoria;
import entidades.Plataforma;
import entidades.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProductoDAO extends GenericDAO<Producto, Long> implements IProductoDAO {

    public ProductoDAO() {
        super(Producto.class);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                "SELECT p FROM Producto p WHERE p.nombreProducto LIKE :nombre", Producto.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorPlataforma(Plataforma plataforma) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                "SELECT p FROM Producto p WHERE p.plataforma = :plataforma", Producto.class);
            query.setParameter("plataforma", plataforma);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarPorCategoria(Categoria categoria) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                "SELECT p FROM Producto p JOIN p.videojuego v JOIN v.categorias c WHERE c = :categoria", Producto.class);
            query.setParameter("categoria", categoria);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}