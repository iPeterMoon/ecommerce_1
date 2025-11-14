package DAO.interfaces;


import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD.
 * @param <T> El tipo de la entidad 
 * @param <ID> El tipo del ID de la entidad 
 */
public interface IGenericDAO<T, ID> {
    
    /**
     * Guarda una nueva entidad en la base de datos.
     * @param entity La entidad a guardar.
     */
    void crear(T entity);

    /**
     * Busca una entidad por su ID.
     * @param id El ID de la entidad.
     * @return La entidad encontrada, o null si no existe.
     */
    T buscarPorId(ID id);

    /**
     * Actualiza una entidad existente en la base de datos.
     * @param entity La entidad con los datos actualizados.
     */
    void actualizar(T entity);

    /**
     * Elimina una entidad de la base de datos.
     * @param entity La entidad a eliminar.
     */
    void eliminar(T entity);

    /**
     * Devuelve una lista de todas las entidades de este tipo.
     * @return Lista de entidades.
     */
    List<T> buscarTodos();
}