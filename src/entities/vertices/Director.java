package entities.vertices;

import entities.EdgeDirectorToMovie;
//import entities.EdgeGenreToMovie;

import java.util.ArrayList;

public class Director {
    private String name;
    private ArrayList<EdgeDirectorToMovie> moviesEdges;

    public Director(String name) {
        this.name = name;
        this.moviesEdges = new ArrayList<EdgeDirectorToMovie>();
    }

    public void addMovieEdge(Movie endMovie) {
        this.moviesEdges.add(new EdgeDirectorToMovie(this, endMovie));
    }

    public void printMovies(){
        for(EdgeDirectorToMovie e: this.moviesEdges) {
            System.out.println(" -> " + e.getEnd().getTitle() + ": " + e.getEnd().getGenre() + " | " +
            Integer.toString(e.getEnd().getYear()));
        }
    }

    public String getName() {
        return this.name;
    }

}
