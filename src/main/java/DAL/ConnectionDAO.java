package DAL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDAO {
    private static EntityManagerFactory em = Persistence.createEntityManagerFactory("traitement-data");
    public ConnectionDAO() {
    }
    public static EntityManager getConnection() {
        return em.createEntityManager();
    }
}
