package DAL;

import BO.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreDAO {
    private static EntityManager em;

    public GenreDAO(EntityManager em) {
        this.em = em;
    }


    public static void saveGenre(Genre genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }

    public Genre getGenre(Genre genre) {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.genre = :genre", Genre.class);
        query.setParameter("genre", genre.getGenre());
        List<Genre> reqGenre= query.getResultList();

        if (reqGenre.isEmpty()){
            return null;
        }else {
            return reqGenre.get(0);
        }
    }
}
