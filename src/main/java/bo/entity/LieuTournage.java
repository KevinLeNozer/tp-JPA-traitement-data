package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LieuTournage")
public class LieuTournage {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdLieuTournage")
    @GenericGenerator(name = "seqIdLieuTournage", strategy = "increment")
    private long id;

    @Column(name = "ville", length = 175, nullable = false)
    private String ville;

    @Column(name = "etatDpt", length = 170, nullable = false)
    private String etatDpt;

    @Column(name = "pays", length = 170, nullable = false)
    private String pays;

    @OneToMany(mappedBy = "lieuTournage", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Film> filmLieuTournageList = new ArrayList<>();

    public List<Film> getfilmLieuTournageList() {
        return filmLieuTournageList;
    }

    public void setfilmLieuTournageList(List<Film> filmLieuTournageList) {
        this.filmLieuTournageList = filmLieuTournageList;
    }
}
