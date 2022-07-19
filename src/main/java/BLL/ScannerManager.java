package BLL;

import BO.entity.Acteur;
import BO.entity.Film;
import DAL.ActeurDAO;
import DAL.FilmDAO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ScannerManager {
    private static ActeurDAO implActeur;
    private static FilmDAO implFilm;

    public ScannerManager(EntityManager em) {
        implActeur = new ActeurDAO(em);
        implFilm = new FilmDAO(em);
    }
    public static List<Film> selectFilmByActeur(String nomActeur) {
        Acteur acteur = implActeur.selectFilmByActeur(nomActeur);
        return acteur.getFilmListActeurs();
    }
    public static List<Acteur> selectCastingByFilm(String filmNom) {
        Film film = implFilm.selectCastingByFilm(filmNom);
        return film.getCastingPrincipal();
    }
    public static List<Film> selectFilmByAnneeSortie(String dateSortie1, String dateSortie2) {
        List<Film> film = implFilm.selectFilmByDate(dateSortie1, dateSortie2);
        return film;
    }
    public static List<Film> selectFilmBy2Acteur(String acteur1, String acteur2) {
        List<Film> film = implFilm.selectFilmBy2Acteur(acteur1, acteur2);
        return film;
    }
}
