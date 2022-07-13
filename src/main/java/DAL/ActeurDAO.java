package DAL;

import BO.entity.Acteur;
import BO.entity.Film;
import BO.entity.Genre;

import javax.persistence.EntityManager;

public class ActeurDAO {
    private static EntityManager em;

    public ActeurDAO() {
        this.em = ConnectionDAO.getConnection();
    }

    public static void saveActeur(Acteur acteur) {
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }

    public static void saveFilm(Film film) {
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }

    public static void saveGenre(Genre genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }
}
