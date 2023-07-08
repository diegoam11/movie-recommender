package entities.vertices;

import entities.EdgeGenreToMovie;

import java.util.ArrayList;
import java.util.HashMap;

public class Genre {
    private String genre;
    private HashMap<String, EdgeGenreToMovie> moviesEdges;
    private int popularity;
;
    public Genre(String genre) {
        this.genre = genre.toLowerCase();
        this.moviesEdges = new HashMap<>();
        this.popularity = 0;
    }

    public void addMovieEdge(String title ,Movie endMovie) {
        this.moviesEdges.put(title, new EdgeGenreToMovie(this, endMovie));
    }

    public HashMap<String, EdgeGenreToMovie> getMoviesEdges(){
        return this.moviesEdges;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setPopularity() {
        this.popularity += 1;
    }
}
