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

}
