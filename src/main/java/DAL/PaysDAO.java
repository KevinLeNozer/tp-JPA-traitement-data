package DAL;

import BO.entity.Pays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
public class PaysDAO {
    private static EntityManager em;
    public PaysDAO(EntityManager em) {
        this.em = em;
    }
    public static void savePays(Pays pays) {
        em.getTransaction().begin();
        em.persist(pays);
        em.getTransaction().commit();
    }
    public Pays getPays(Pays pays) {
        TypedQuery<Pays> query = em.createQuery("SELECT p FROM Pays p WHERE p.nomPays =:nomPays", Pays.class);
        query.setParameter("nomPays", pays.getNomPays());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
