package DAL;

import BO.entity.Film;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FilmDAO {
    private static EntityManager em;

    public FilmDAO(EntityManager em) {
        this.em = em;
    }
    public static void saveFilm(Film film) {
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }
    public Film getFilm(Film film) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.imdId = :imdId", Film.class);
        query.setParameter("imdId", film.getImdId());
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }


}
