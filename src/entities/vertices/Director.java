package entities.vertices;

import entities.EdgeDirectorToMovie;
//import entities.EdgeGenreToMovie;

import java.util.ArrayList;
import java.util.HashMap;

public class Director {
    private String name;
    private HashMap<String, EdgeDirectorToMovie> moviesEdges;
    private int popularity;

    public Director(String name) {
        this.name = name.toLowerCase();
        this.moviesEdges = new HashMap<>();
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
    public int getPopularity() {
        return this.popularity;
    }

    public void setPopularity() {
        this.popularity += 1;
    }
}
