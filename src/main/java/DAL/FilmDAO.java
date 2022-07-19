package DAL;

import BO.entity.Acteur;
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
    public Film selectCastingByFilm(String nomFilm) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.nom =:nomFilm", Film.class);
        query.setParameter("nomFilm", nomFilm);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
    public List<Film> selectFilmByDate(String dateSortie1, String dateSortie2) {
        TypedQuery<Film> query = em.createQuery("SELECT f FROM Film f WHERE f.anneeSortie >= :dateSortie1 AND f.anneeSortie <= :dateSortie2", Film.class);
        query.setParameter("dateSortie1", dateSortie1);
        query.setParameter("dateSortie2", dateSortie2);
        return query.getResultList();
    }
    public List<Film> selectFilmBy2Acteur(String acteur1, String acteur2) {
        TypedQuery<Film> query = em.createQuery("SELECT DISTINCT f FROM Film f JOIN f.acteurs a WHERE a" +
                ".personne.identite = :acteur1 AND f.id IN (SELECT f.id FROM Film f JOIN f" +
                ".acteurs a WHERE a.personne.identite = :acteur2)", Film.class);
        query.setParameter("acteur1", acteur1);
        query.setParameter("acteur2", acteur2);
        return query.getResultList();
    }
}
