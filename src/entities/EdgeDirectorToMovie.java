package entities;

import entities.vertices.Director;
import entities.vertices.Movie;

public class EdgeDirectorToMovie {
    private Director start;
    private Movie end;

    public EdgeDirectorToMovie(Director startDirector, Movie endMovie) {
        this.start = startDirector;
        this.end = endMovie;
    }

    public Director getStart() {
        return this.start;
    }

    public Movie getEnd() {
        return this.end;
    }

}