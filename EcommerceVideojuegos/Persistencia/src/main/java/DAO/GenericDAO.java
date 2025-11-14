package DAO;

import DAO.interfaces.IGenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import util.JPAUtil; 

/**
 * Implementación genérica de IGenericDAO.
 */
public class GenericDAO<T, ID> implements IGenericDAO<T, ID> {

    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return JPAUtil.getEntityManager();
    }

    @Override
    public void crear(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            em.close();
        }
    }

    @Override
    public T buscarPorId(ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity); // merge es para actualizar
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

    @Override
    public void eliminar(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // Asegurarse de que la entidad esté gestionada antes de eliminar
            if (!em.contains(entity)) {
                entity = em.merge(entity);
            }
            em.remove(entity);
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

    @Override
    public List<T> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            // Usando JPQL (más simple que Criteria API para un "buscarTodos")
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
