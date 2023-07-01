package entities.vertices;

import entities.EdgeGenreToMovie;

import java.util.ArrayList;

public class Genre {
    private String genre;
    private ArrayList<EdgeGenreToMovie> moviesEdges;
;
    public Genre(String genre) {
        this.genre = genre;
        this.moviesEdges = new ArrayList<EdgeGenreToMovie>();
    }

    public void addMovieEdge(Movie endMovie) {
        this.moviesEdges.add(new EdgeGenreToMovie(this, endMovie));
    }

    public void printMovies(){
        for(EdgeGenreToMovie e: this.moviesEdges) {
            System.out.println(" -> " + e.getEnd().getTitle() + ": " + e.getEnd().getGenre() + " | " +
            Integer.toString(e.getEnd().getYear()) + " | " + e.getEnd().getDirector());
        }
    }

    public String getGenre() {
        return this.genre;
    }
}
