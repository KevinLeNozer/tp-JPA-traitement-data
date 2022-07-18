package DAL;

import BLL.ActeurManager;
import BO.entity.Acteur;
import BO.entity.Film;
import BO.entity.Genre;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;

public class ActeurDAO {
    private static EntityManager em;
    public ActeurDAO(EntityManager em) {
        this.em = em;
    }
    public static void saveActeur(Acteur acteur) {
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }
    public Acteur getActeur(Acteur acteur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a WHERE a.personne.identite = :identite",
                Acteur.class);
        query.setParameter("identite", acteur.getPersonne().getIdentite());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
