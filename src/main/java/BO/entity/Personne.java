package BO.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
/**
 * Classe Personne permettant de définir une personne dans la classe Acteur à l'aide de @Embeddable
 */
@Embeddable
public class Personne {

    private String identite;
    private String url;

    public Personne() {
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("identite='").append(identite).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
