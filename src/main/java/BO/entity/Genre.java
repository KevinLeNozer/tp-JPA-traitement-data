package BO.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqIdGenre")
    @GenericGenerator(name = "seqIdGenre", strategy = "increment")
    private long id;

    @Column(name = "genre", length = 170)
    private String genre;

    public Genre() {
    }

    public Genre(long id, String genre, List<Film> filmListGenre) {
        this.id = id;
        this.genre = genre;
        this.filmListGenre = filmListGenre;
    }

    public long getId() {
        return id;
    }

    @ManyToMany(mappedBy = "genres")
    private List<Film> filmListGenre = new ArrayList<>();

    public List<Film> getFilmListGenre() {
        return filmListGenre;
    }

    public void setFilmListGenre(List<Film> filmListGenre) {
        this.filmListGenre = filmListGenre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append('}');
        return sb.toString();
    }
}
