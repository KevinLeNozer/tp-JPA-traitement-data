package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Realisateur")
public class Realisateur extends Personne{

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdRealisateur")
    @GenericGenerator(name = "seqIdRealisateur", strategy = "increment")
    private long id;

    @ManyToMany(mappedBy="realisateurs")
    private List<Film> filmListRealisateur;

    public Realisateur() {
    }

    public Realisateur(String identite, String lieuNaissance, String url, LocalDate dateNaissance) {
        super(identite, lieuNaissance, url, dateNaissance);
    }
}
