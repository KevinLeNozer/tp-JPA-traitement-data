import BLL.ScannerManager;
import BO.entity.Acteur;
import BO.entity.Film;
import DAL.ConnectionDAO;
import DAL.FilmDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class ScannerUser {
    static ConnectionDAO connectionDAO = new ConnectionDAO();
    static EntityManager em = connectionDAO.getConnection();
    static ScannerManager scannerManager = new ScannerManager(em);

    /**
     * Application Scanner qui permet de lancer un programme permettant d'effectuer des opérations de recherche sur une base de données.
     *Un menu est affiché à l'utilisateur pour lui permettre de choisir l'opération à accomplir.
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Affichage de la filmographie d’un acteur donné");
        System.out.println("2. Affichage du casting d’un film donné");
        System.out.println("3. Affichage des films sortis entre 2 années données");
        System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
        System.out.println("5. Affichage des acteurs communs à 2 films donnés");
        System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("7. Fin de l'application");
        int choix = 0;
        while (choix != 7) {
             choix = sc.nextInt();
            if(choix == 1) {
                sc.nextLine();
                System.out.println("Veuillez indiquer un acteur/actrice afin d'avoir sa filmographie.");
                String acteurNom = sc.nextLine();
                List<Film> films = scannerManager.selectFilmByActeur(acteurNom);
                for (Film film : films) {
                    System.out.println("=> " + film.getNom() + "sortie en " + film.getAnneeSortie());
                }
                break;
            } else if (choix == 2) {
                sc.nextLine();
                System.out.println("Veuillez indiquer un film pour avoir son casting.");
                String filmNom = sc.nextLine();
                List<Acteur> acteurList = scannerManager.selectCastingByFilm(filmNom);
                for (Acteur acteur : acteurList) {
                    System.out.println("=> " + acteur.getPersonne().getIdentite());
                }
                break;
            } else if (choix == 3) {
                sc.nextLine();
                System.out.println("Veuillez indiquer la première date.");
                String firstDate = sc.nextLine();
                System.out.println("Veuillez indiquer la deuxième date.");
                String secondDate = sc.nextLine();

                List<Film> filmList = scannerManager.selectFilmByAnneeSortie(firstDate, secondDate);
                for (Film film : filmList) {
                    System.out.println(film.getNom() + "sortie en => " + film.getAnneeSortie());
                }
                break;
            } else if (choix == 4) {
                sc.nextLine();
                System.out.println("Veuillez indiquer un acteur/actrice.");
                String firstActeur = sc.nextLine();
                System.out.println("Veuillez indiquer un second acteur/actrice.");
                String secondActeur = sc.nextLine();
                List<Film> filmListActeur = scannerManager.selectFilmBy2Acteur(firstActeur, secondActeur);
                for (Film filmL : filmListActeur) {
                    System.out.println(filmL.getNom());
                }
                break;
            } else if (choix == 5) {
                sc.nextLine();
                System.out.println("Veuillez indiquer un film.");
                String filmFirst = sc.nextLine();
                System.out.println("Veuillez indiquer un second film.");
                String filmSecond = sc.nextLine();
                List<Acteur> acteurList = scannerManager.selectActeurForTwoFilm(filmFirst, filmSecond);
                for (Acteur acteur : acteurList) {
                    System.out.println("=> " + acteur.getPersonne().getIdentite());
                }
                break;
            } else if (choix == 6) {
                sc.nextLine();
                System.out.println("Veuillez indiquer une première date.");
                String dateFirst = sc.nextLine();
                System.out.println("Veuillez indiquer une seconde date.");
                String dateSecond = sc.nextLine();
                System.out.println("Veuillez indiquer un acteur/actrice.");
                String nomActeur = sc.nextLine();

                List<Film> films = scannerManager.selectFilmByDateWithActeur(dateFirst, dateSecond, nomActeur);
                for (Film film : films) {
                    System.out.println(film.getNom());
                }
                break;
            } else if (choix == 7){
                System.out.println("Fin de l'application");
            }
            sc.close();
        }
    }
}