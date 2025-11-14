package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase para gestionar el EntityManagerFactory de JPA con el patron Singleton.
 * Sigue el patrón Singleton para asegurar una sola instancia.
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "ecommerceVideojuegosPU";
    
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Error al inicializar EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void close() {
        if (factory != null) {
            factory.close();
        }
    }
}