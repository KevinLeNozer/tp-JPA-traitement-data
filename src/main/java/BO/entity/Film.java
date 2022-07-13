package BO.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Film")
public class Film {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdFilm")
    @GenericGenerator(name = "seqIdFilm", strategy = "increment")
    private long id;

    @Column(name = "nom", length = 255)
    private String nom;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "anneeSortie")
    private LocalDate anneeSortie;

    @Column(name = "langue", length = 15)
    private String langue;

    public Film() {
    }

    public Film(String nom, String url, String description, LocalDate anneeSortie, String langue,
                List<Realisateur> realisateurs, List<Acteur> acteurs, List<Genre> genres, List<Acteur> castingPrincipal, LieuTournage lieuTournage, List<Role> roles, Pays pays) {
        this.nom = nom;
        this.url = url;
        this.description = description;
        this.anneeSortie = anneeSortie;
        this.langue = langue;
        this.realisateurs = realisateurs;
        this.acteurs = acteurs;
        this.genres = genres;
        this.castingPrincipal = castingPrincipal;
        this.lieuTournage = lieuTournage;
        this.roles = roles;
        this.pays = pays;
    }

    @ManyToMany
    @JoinTable(name = "Realisateur_Film",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "realisateurs_Id"))
    private List<Realisateur> realisateurs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Film_acteurs",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "acteurs_Id"))
    private List<Acteur> acteurs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Genre_films",
            joinColumns = @JoinColumn(name = "film_Id"),
            inverseJoinColumns = @JoinColumn(name = "genres_Id"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(LocalDate anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", anneeSortie=").append(anneeSortie);
        sb.append(", langue='").append(langue).append('\'');
        sb.append(", realisateurs=").append(realisateurs);
        sb.append(", acteurs=").append(acteurs);
        sb.append(", genres=").append(genres);
        sb.append(", castingPrincipal=").append(castingPrincipal);
        sb.append(", lieuTournage=").append(lieuTournage);
        sb.append(", roles=").append(roles);
        sb.append(", pays=").append(pays);
        sb.append('}');
        return sb.toString();
    }
}
