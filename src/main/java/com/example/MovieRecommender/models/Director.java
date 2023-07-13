package com.example.MovieRecommender.models;

import com.example.MovieRecommender.models.edges.EdgeDirectorToMovie;

import java.util.HashMap;
import jakarta.persistence.*;
@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;
    private String name;
    @Transient
    private HashMap<String, EdgeDirectorToMovie> moviesEdges;

    public Director(String name) {
        this.name = name.toLowerCase();
        this.moviesEdges = new HashMap<>();
    }

    public Director() {

    }

    public void addMovieEdge(String title, Movie endMovie) {
        this.moviesEdges.put(title, new EdgeDirectorToMovie(this, endMovie));
    }

    public HashMap<String, EdgeDirectorToMovie> getMoviesEdges() {
        return this.moviesEdges;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

}
