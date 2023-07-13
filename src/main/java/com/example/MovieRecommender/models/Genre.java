package com.example.MovieRecommender.models;

import com.example.MovieRecommender.models.edges.EdgeGenreToMovie;
import jakarta.persistence.*;

import java.util.HashMap;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;
    private String genre;
    @Transient
    private HashMap<String, EdgeGenreToMovie> moviesEdges;

    public Genre() {

    }

    public Genre(String genre) {
        this.genre = genre.toLowerCase();
        this.moviesEdges = new HashMap<>();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void addMovieEdge(String title ,Movie endMovie) {
        this.moviesEdges.put(title, new EdgeGenreToMovie(this, endMovie));
    }

    public HashMap<String, EdgeGenreToMovie> getMoviesEdges(){
        return this.moviesEdges;
    }

}
