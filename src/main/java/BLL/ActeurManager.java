package BLL;

import BO.entity.*;
import DAL.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActeurManager {

    private static volatile ActeurManager instance = null;

    private static ActeurDAO impl = new ActeurDAO();
    private static GenreDAO impls = new GenreDAO();

    public ActeurManager() {
    }

    public void saveActeur(JSONObject a) {
        Acteur acteur = parseActeurObject(a);
        if (validerActeur(acteur)) {
            impl.saveActeur(acteur);
        }
    }

    public void saveFilm(JSONObject f) {
        Film film = parseFilmObject(f);
        if (validerFilm(film)) {
            impl.saveFilm(film);
        }
    }

    public Boolean validerActeur(Acteur acteur) {
        return true;
    }
    public Boolean validerFilm(Film film) {
        return true;
    }
    public Boolean validerGenre(Genre genre) {
        return true;
    }
    public Boolean validerRealisateur(Realisateur realisateur) {
        return true;
    }

    public static Acteur parseActeurObject(JSONObject a) {

        JSONObject naissance = (JSONObject) a.get("naissance");

        Object dateNaissance = naissance.get("dateNaissance");

        String[] newNaissance = ((String) dateNaissance).split("-");

        Acteur acteur = new Acteur();
        Personne personne = new Personne();

        personne.setIdentite((String) a.get("identite"));
        personne.setUrl((String) a.get("url"));

        acteur.setPersonne(personne);

        JSONArray rolesListJson = (JSONArray) a.get("roles");

        List<Role> roleList = new ArrayList<>();

        for (Object o : rolesListJson) {
            roleList.add(parseRoleObject((JSONObject) o));
        }

        for (Role role : roleList) {
            role.setActeur(acteur);
        }

        acteur.setRoles(roleList);

        acteur.setDateNaissance(LocalDate.of(
                Integer.parseInt(newNaissance[0]),
                Integer.parseInt(newNaissance[1]),
                Integer.parseInt(newNaissance[2]))
        );
        acteur.setLieuNaissance((String) naissance.get("lieuNaissance"));

        System.out.println(acteur);
        return acteur;
    }

    public static Role parseRoleObject(JSONObject r) {
        Role role = new Role();

        //System.out.println(r.get("characterName"));

        if (r.get("characterName") != null) {
            role.setCharacterName((String)r.get("characterName"));
        }
        role.setFilm(parseFilmObject((JSONObject) r.get("film")));

        return role;
    }

    public static Film parseFilmObject(JSONObject f) {

        Film film = new Film();

        film.setNom(f.get("nom").toString());

        if (f.get("plot") != null) {
            film.setDescription(f.get("plot").toString());
        }
        if (f.get("langue") != null) {
            film.setLangue(f.get("langue").toString());
        }

        film.setUrl(f.get("url").toString());

        JSONArray genreListJson = (JSONArray) f.get("genres");

        List<Genre> genresList = new ArrayList<>();

        for (Object o : genreListJson) {
            genresList.add(parseGenreObject((String) o));
        }

        for (Genre genre : genresList) {
            genre.getFilmListGenre().add(film);
            impl.saveGenre(genre);
        }

        film.setGenres(genresList);
        return film;
    }

    public static Genre parseGenreObject(String g) {

        Genre genre = new Genre();

        genre.setGenre(g);

        if (findGenreByGenre(genre) ==  null) {
            return genre;
        } else {
            return findGenreByGenre(genre);
        }
    }

    public static Genre findGenreByGenre(Genre genre) {
        return impls.findGenreByGenre(genre);
    }

    public static Realisateur parseRealisateurObject(JSONObject r) {
        Realisateur realisateur = new Realisateur();

        Personne personne = new Personne();

        personne.setIdentite((String) r.get("identite"));
        personne.setUrl((String) r.get("url"));

        realisateur.setPersonne(personne);

        return realisateur;
    }
}
