import BLL.ScannerManager;
import BO.entity.Film;
import DAL.ConnectionDAO;
import DAL.FilmDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class ScannerUser {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Affichage de la filmographie d’un acteur donné");
        System.out.println("2. Affichage du casting d’un film donné");
        System.out.println("3. Affichage des films sortis entre 2 années données");
        System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
        System.out.println("5. Affichage des acteurs communs à 2 films donnés");
        System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("7. Fin de l'application");

        while (sc.hasNext()) {
            if(sc.nextLine().equals("1")) {
                System.out.println("Veuillez indiquer un acteur");
                String acteurNom = sc.nextLine();
                break;
            } else if (sc.nextInt() == 2) {

                break;
            } else if (sc.nextInt() == 3) {

                break;
            } else if (sc.nextInt() == 4) {

                break;
            } else if (sc.nextInt() == 5) {

                break;
            } else if (sc.nextInt() == 6) {

                break;
            }else if(sc.nextInt() == 7) {
                System.out.println("Fin de l'application");
                sc.close();
            }
        }
    }
}