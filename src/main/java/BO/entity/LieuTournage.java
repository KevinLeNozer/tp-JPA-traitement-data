package BO.entity;

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

    public long getId() {
        return id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtatDpt() {
        return etatDpt;
    }

    public void setEtatDpt(String etatDpt) {
        this.etatDpt = etatDpt;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public List<Film> getFilmLieuTournageList() {
        return filmLieuTournageList;
    }

    public void setFilmLieuTournageList(List<Film> filmLieuTournageList) {
        this.filmLieuTournageList = filmLieuTournageList;
    }

    @OneToMany(mappedBy = "lieuTournage", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Film> filmLieuTournageList = new ArrayList<>();

}
