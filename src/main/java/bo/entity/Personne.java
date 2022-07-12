package bo.entity;

import javax.persistence.Inheritance;
import java.time.LocalDate;

@Inheritance
public abstract class Personne {
    private String identite;
    private String lieuNaissance;
    private String url;
    private LocalDate dateNaissance;

    public Personne() {
    }

    public Personne(String identite, String lieuNaissance, String url, LocalDate dateNaissance) {
        this.identite = identite;
        this.lieuNaissance = lieuNaissance;
        this.url = url;
        this.dateNaissance = dateNaissance;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
