package BO.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Acteur")
public class Acteur {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdActeur")
    @GenericGenerator(name = "seqIdActeur", strategy = "increment")
    private long id;
    @Embedded
    private Personne personne;

    @Column(name = "lieuNaissance", length = 150)
    private String lieuNaissance;

    @Column(name = "dateNaissance", length = 30)
    private LocalDate dateNaissance;

    @ManyToMany(mappedBy="acteurs")
    private List<Film> filmListActeurs = new ArrayList<>();

    @ManyToMany(mappedBy="castingPrincipal")
    private List<Film> filmListcastingPrincipal = new ArrayList<>();

    @OneToMany(mappedBy = "acteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    public Acteur() {
    }

    public long getId() {
        return id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Film> getFilmListActeurs() {
        return filmListActeurs;
    }

    public void setFilmListActeurs(List<Film> filmListActeurs) {
        this.filmListActeurs = filmListActeurs;
    }

    public List<Film> getFilmListcastingPrincipal() {
        return filmListcastingPrincipal;
    }

    public void setFilmListcastingPrincipal(List<Film> filmListcastingPrincipal) {
        this.filmListcastingPrincipal = filmListcastingPrincipal;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acteur{");
        sb.append("id=").append(id);
        sb.append(", personne=").append(personne);
        sb.append(", lieuNaissance='").append(lieuNaissance).append('\'');
        sb.append(", dateNaissance=").append(dateNaissance);
        sb.append(", filmListActeurs=").append(filmListActeurs);
        sb.append(", filmListcastingPrincipal=").append(filmListcastingPrincipal);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
