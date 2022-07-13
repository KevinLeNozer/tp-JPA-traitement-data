package BO.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pays")
public class Pays {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdPays")
    @GenericGenerator(name = "seqIdPays", strategy = "increment")
    private long id;

    @Column(name = "nomPays", length = 170, nullable = false)
    private String nomPays;

    @Column(name = "url", length = 255, nullable = false)
    private String url;

    @OneToMany(mappedBy = "pays", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Film> filmPays = new ArrayList<>();

    public List<Film> getfilmPays() {
        return filmPays;
    }

    public void setfilmPays(List<Film> filmPays) {
        this.filmPays = filmPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Film> getFilmPays() {
        return filmPays;
    }

    public void setFilmPays(List<Film> filmPays) {
        this.filmPays = filmPays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pays{");
        sb.append("nomPays='").append(nomPays).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
