package DAL;

import BO.entity.LieuTournage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class LieuTournageDAO {
    private static EntityManager em;
    public LieuTournageDAO(EntityManager em) {
        this.em = em;
    }
    public static void saveLieuTournage(LieuTournage lieuTournage) {
        em.getTransaction().begin();
        em.persist(lieuTournage);
        em.getTransaction().commit();
    }
    public LieuTournage getLieuTournage(LieuTournage lieuTournage) {
        TypedQuery<LieuTournage> query =
                em.createQuery("SELECT lt FROM LieuTournage lt WHERE lt.ville =:ville", LieuTournage.class);
        query.setParameter("ville", lieuTournage.getVille());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
