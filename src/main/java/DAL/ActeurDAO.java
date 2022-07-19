package DAL;

import BLL.ActeurManager;
import BO.entity.Acteur;
import BO.entity.Film;
import BO.entity.Genre;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    public Acteur selectFilmByActeur(String nomActeur) {
        TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a where a.personne.identite =:identite", Acteur.class);
        query.setParameter("identite", nomActeur);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public List<Acteur> selectActeurForTwoFilm(String film1, String film2) {
        TypedQuery<Film> query1 = em.createQuery("SELECT f FROM Film f WHERE f.nom =:film1",
                Film.class);
        TypedQuery<Film> query2 = em.createQuery("SELECT f2 FROM Film f2 WHERE f2.nom =:film2",
                Film.class);
        query1.setParameter("film1", film1);
        query2.setParameter("film2", film2);

        List<Acteur> acteurList = new ArrayList<>();

        if (query1.getResultList().size() > 0 && query2.getResultList().size() > 0) {
            for (Acteur acteur : query1.getResultList().get(0).getActeurs()) {
                if (query2.getResultList().get(0).getActeurs().contains(acteur)) {
                    acteurList.add(acteur);
                }
            }
        }
        return acteurList;
    }
}
