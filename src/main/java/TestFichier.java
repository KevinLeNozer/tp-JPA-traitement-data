import BLL.ActeurManager;
import BO.entity.*;
import DAL.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe qui permet de lancer l'application qui met en base les éléments du fichier JSON
 */
import static BLL.ActeurManager.parseFilmObject;
public class TestFichier {
    static ConnectionDAO connectionDAO = new ConnectionDAO();
    static EntityManager em = connectionDAO.getConnection();
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(
                "C:\\Users\\kevin\\Desktop\\IdeaProjects\\traitement-data\\src\\main\\resources\\films.json"))
        {
            Object obj = jsonParser.parse(reader);

            JSONArray acteurListJson = (JSONArray) obj;

            JSONArray filmListJson = (JSONArray) obj;

            ActeurManager acteurManager = new ActeurManager(em);

            for (Object o : filmListJson) {
                acteurManager.saveActeur((JSONObject) o);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}