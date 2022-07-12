package bo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Acteur")
public class Acteur extends Personne{
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdActeur")
    @GenericGenerator(name = "seqIdActeur", strategy = "increment")
    private long id;

    @ManyToMany(mappedBy="acteurs")
    private List<Film> filmListActeurs;

    @ManyToMany(mappedBy="castingPrincipal")
    private List<Film> filmListcastingPrincipal;

    @OneToMany(mappedBy = "acteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Acteur() {
    }

    public Acteur(String identite, String lieuNaissance, String url, LocalDate dateNaissance) {
        super(identite, lieuNaissance, url, dateNaissance);
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acteur{");
        sb.append(super.toString());
        sb.append("id=").append(id);
        sb.append(", filmListActeurs=").append(filmListActeurs);
        sb.append(", filmListcastingPrincipal=").append(filmListcastingPrincipal);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
