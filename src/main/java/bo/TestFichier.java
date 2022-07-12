package bo;

import bo.entity.Acteur;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestFichier {

    static EntityManagerFactory em = Persistence.createEntityManagerFactory("traitement-data");

    static EntityManager et = em.createEntityManager();
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(
                "C:\\Users\\kevin\\Desktop\\IdeaProjects\\traitement-data\\src\\main\\resources\\films.json"))
        {
            Object obj = jsonParser.parse(reader);

            JSONArray acteurList = (JSONArray) obj;
            System.out.println(acteurList);

            acteurList.forEach(emp -> parseActeurObject( (JSONObject) emp) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        et.getTransaction().begin();

//        et.getTransaction().commit();
    }
    public  static void parseActeurObject(JSONObject a) {
        Acteur acteur = new Acteur();
        acteur.setIdentite((String) a.get("identite"));
        System.out.println(acteur);
    }
}
