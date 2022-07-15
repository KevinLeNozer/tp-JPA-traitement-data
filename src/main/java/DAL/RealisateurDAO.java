package DAL;

import BO.entity.Acteur;
import BO.entity.Realisateur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RealisateurDAO {
    private static EntityManager em;

    public RealisateurDAO(EntityManager em) {
        this.em = em;
    }

    public static void saveRealisateur(Realisateur realisateur) {
        em.getTransaction().begin();
        em.persist(realisateur);
        em.getTransaction().commit();
    }

    public Realisateur getRealisateur(Realisateur realisateur) {
        TypedQuery<Realisateur> query =
                em.createQuery("SELECT r FROM Realisateur r WHERE r.personne.identite " + "=:identite",
                        Realisateur.class);
        query.setParameter("identite", realisateur.getPersonne().getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
