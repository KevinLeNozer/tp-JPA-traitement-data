package BO.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Realisateur")
public class Realisateur {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdRealisateur")
    @GenericGenerator(name = "seqIdRealisateur", strategy = "increment")
    private long id;

    @ManyToMany(mappedBy="realisateurs")
    private List<Film> filmListRealisateur = new ArrayList<>();

    @Embedded
    private Personne personne;

    public Realisateur() {
    }

    public long getId() {
        return id;
    }

    public List<Film> getFilmListRealisateur() {
        return filmListRealisateur;
    }

    public void setFilmListRealisateur(List<Film> filmListRealisateur) {
        this.filmListRealisateur = filmListRealisateur;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Realisateur{");
        sb.append("id=").append(id);
        sb.append(", filmListRealisateur=").append(filmListRealisateur);
        sb.append(", personne=").append(personne);
        sb.append('}');
        return sb.toString();
    }
}
