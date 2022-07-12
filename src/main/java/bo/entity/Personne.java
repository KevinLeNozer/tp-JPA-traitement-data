package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Personne")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdPersonne")
    @GenericGenerator(name = "seqIdPersonne", strategy = "increment")
    private long id;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("identite='").append(identite).append('\'');
        sb.append(", lieuNaissance='").append(lieuNaissance).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", dateNaissance=").append(dateNaissance);
        sb.append('}');
        return sb.toString();
    }
}
