package bo.entity;

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
}
