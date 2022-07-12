package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Film")
public class Film {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdFilm")
    @GenericGenerator(name = "seqIdFilm", strategy = "increment")
    private long id;

    @Column(name = "url", length = 255, nullable = false)
    private String url;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "anneeSortie")
    private LocalDate anneeSortie;

    @Column(name = "langue", length = 15, nullable = false)
    private String langue;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Realisateur_Film",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "realisateurs_Id"))
    private List<Realisateur> realisateurs = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Film_acteurs",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "acteurs_Id"))
    private List<Acteur> acteurs = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Genre_films",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "genres_Id"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "CastingPrincipal",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_Id"))
    private List<Acteur> castingPrincipal = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "lieu_tournage_id")
    private LieuTournage lieuTournage;

    @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pays_id")
    private Pays pays;

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LieuTournage getLieuTournage() {
        return lieuTournage;
    }

    public void setLieuTournage(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    public List<Acteur> getCastingPrincipal() {
        return castingPrincipal;
    }

    public void setCastingPrincipal(List<Acteur> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Acteur> getActeurs() {
        return acteurs;
    }



    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }
}
