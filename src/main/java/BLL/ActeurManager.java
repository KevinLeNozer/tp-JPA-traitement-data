package BLL;

import BO.entity.*;
import DAL.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActeurManager {
    private static volatile ActeurManager instance = null;
    private static ActeurDAO implActeur;
    private static GenreDAO implGenre;
    private static FilmDAO implFilm;
    private static  RealisateurDAO implRealisateur;
    private static LieuTournageDAO implLieuTournage;
    private static PaysDAO implPays;
    private static RoleDAO implRole;

    public ActeurManager(EntityManager em) {
        implActeur = new ActeurDAO(em);
        implGenre = new GenreDAO(em);
        implFilm = new FilmDAO(em);
        implRealisateur = new RealisateurDAO(em);
        implLieuTournage = new LieuTournageDAO(em);
        implPays = new PaysDAO(em);
        implRole = new RoleDAO(em);
    }

    public final static ActeurManager getInstance(EntityManager em) {
        if (ActeurManager.instance == null) {
            synchronized (ActeurManager.class) {
                if (ActeurManager.instance == null){
                    ActeurManager.instance = new ActeurManager(em);
                }
            }
        }
        return ActeurManager.instance;
    }

    public void saveActeur(JSONObject a) {
        Acteur acteur = parseActeurObject(a);
        if (validerActeur(acteur)) {
            implActeur.saveActeur(acteur);
        }
    }
    public void saveFilm(JSONObject f) {
        Film film = parseFilmObject(f);
        if (validerFilm(film)) {
            implFilm.saveFilm(film);
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
        List<Film> filmList = new ArrayList<>();

        for (Object o : rolesListJson) {
            Role role = parseRoleObject((JSONObject) o);
            roleList.add(role);
            filmList.add(role.getFilm());
        }

        for (Role role : roleList) {
            //Liaison acteur aux roles
            role.setActeur(acteur);
        }

        for (Film film : filmList) {
            //Liaison acteur aux films
            film.getActeurs().add(acteur);
        }

        //Lisaison Role à l'acteur
        acteur.setRoles(roleList);
//        acteur.getFilmListActeurs();
        acteur.setDateNaissance(LocalDate.of(
                Integer.parseInt(newNaissance[0]),
                Integer.parseInt(newNaissance[1]),
                Integer.parseInt(newNaissance[2]))
        );
        acteur.setLieuNaissance((String) naissance.get("lieuNaissance"));

        return getActeur(acteur);
    }

    public static Acteur getActeur(Acteur acteur) {
        //Si un acteur n'est pas trouvé on le crée
        if (implActeur.getActeur(acteur) == null) {
            implActeur.saveActeur(acteur);
            return acteur;
        } else {
            //Sinon on retourne le acteur trouvé sans faire de doublon
            return implActeur.getActeur(acteur);
        }
    }

    public static Role parseRoleObject(JSONObject r) {
        Role role = new Role();
        if (r.get("characterName") != null) {
            role.setCharacterName((String)r.get("characterName"));
        }
        role.setFilm(parseFilmObject((JSONObject) r.get("film")));

        return getRole(role);
    }

    public static Role getRole(Role role) {
        //Si un film n'est pas trouvé on le crée
        if (implRole.getRole(role) == null) {
            implRole.saveRole(role);
            return role;
        } else {
            //Sinon on retourne le film trouvé sans faire de doublon
            return implRole.getRole(role);
        }
    }


    public static Film parseFilmObject(JSONObject f) {

        Film film = new Film();

        if (f.get("plot") == null) {
            film.setDescription("La description du film est manquant");
        }
        film.setNom(f.get("nom").toString());
        film.setImdId(f.get("id").toString());
        film.setAnneeSortie(f.get("anneeSortie").toString());

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
        }

        JSONArray realistListJson = (JSONArray) f.get("realisateurs");

        List<Realisateur> realisateurList = new ArrayList<>();
        for (Object o : realistListJson) {
            realisateurList.add(parseRealisateurObject((JSONObject) o));
        }
        for (Realisateur realisateur : realisateurList) {
            realisateur.getFilmListRealisateur().add(film);
        }

        if (f.get("lieuTournage") != null) {
            LieuTournage lieuTournage = parseLieuTournageObject((JSONObject) f.get("lieuTournage"));
            film.setLieuTournage(lieuTournage);
        }

        if (f.get("pays") != null) {
            Pays pays = parsePaysObject((JSONObject) f.get("pays"));
            film.setPays(pays);
        }

        film.setGenres(genresList);
        film.setRealisateurs(realisateurList);

        //return la verification des film
        return getFilm(film);
    }

    public static Film getFilm(Film film) {
        //Si un film n'est pas trouvé on le crée
        if (implFilm.getFilm(film) == null) {
            implFilm.saveFilm(film);
            return film;
        } else {
            //Sinon on retourne le film trouvé sans faire de doublon
            return implFilm.getFilm(film);
        }
    }

    public static Pays parsePaysObject(JSONObject p) {
        Pays pays = new Pays();

        pays.setNomPays(p.get("nom").toString());
        pays.setUrl(p.get("url").toString());


        return getPays(pays);
    }

    public static Pays getPays(Pays pays) {
        //Si un genre n'est pas trouvé on le crée
        if (implPays.getPays(pays) == null) {
            implPays.savePays(pays);
            return pays;
        } else {
            //Sinon on retourne le genre trouvé sans faire de doublon
            return implPays.getPays(pays);
        }
    }

    public static LieuTournage parseLieuTournageObject(JSONObject l) {
        LieuTournage lieuTournage = new LieuTournage();

        lieuTournage.setVille(l.get("ville").toString());
        lieuTournage.setEtatDpt(l.get("etatDept").toString());
        lieuTournage.setPays(l.get("pays").toString());

        return getLieuTournage(lieuTournage);
    }

    public static LieuTournage getLieuTournage(LieuTournage lieuTournage) {
        //Si un genre n'est pas trouvé on le crée
        if (implLieuTournage.getLieuTournage(lieuTournage) == null) {
            implLieuTournage.saveLieuTournage(lieuTournage);
            return lieuTournage;
        } else {
            //Sinon on retourne le genre trouvé sans faire de doublon
            return implLieuTournage.getLieuTournage(lieuTournage);
        }
    }


    public static Genre parseGenreObject(String g) {

        Genre genre = new Genre();

        genre.setGenre(g);

        return getGenre(genre);
    }

    public static Genre getGenre(Genre genre) {
        //Si un genre n'est pas trouvé on le crée
        if (implGenre.getGenre(genre) == null) {
            implGenre.saveGenre(genre);
            return genre;
        } else {
            //Sinon on retourne le genre trouvé sans faire de doublon
            return implGenre.getGenre(genre);
        }
    }

    public static Realisateur parseRealisateurObject(JSONObject r) {
        Realisateur realisateur = new Realisateur();

        Personne personne = new Personne();

        personne.setIdentite((String) r.get("identite"));
        personne.setUrl((String) r.get("url"));

        realisateur.setPersonne(personne);

        return getRealisateur(realisateur);
    }

    public static Realisateur getRealisateur(Realisateur realisateur) {
        //Si un realisateur n'est pas trouvé on le crée
        if (implRealisateur.getRealisateur(realisateur) == null) {
            implRealisateur.saveRealisateur(realisateur);
            return realisateur;
        } else {
            //Sinon on retourne le realisateur trouvé sans faire de doublon
            return implRealisateur.getRealisateur(realisateur);
        }
    }
}