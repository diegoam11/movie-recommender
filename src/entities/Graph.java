package entities;

import entities.vertices.Director;
import entities.vertices.Genre;
import entities.vertices.Movie;

import java.util.ArrayList;
import java.util.Objects;

public class Graph {

    private  ArrayList<Movie> movies;
    private ArrayList<Genre> genres;
    private ArrayList<Director> directors;
    private boolean isDirected;

    public Graph(boolean inputIsDirected) {
        this.movies = new ArrayList<Movie>();
        this.genres = new ArrayList<Genre>();
        this.directors = new ArrayList<Director>();
        this.isDirected = inputIsDirected;
    }

    public Movie addMovie(String title, String genre, int year, String director) {
        Movie newMovie = new Movie(title, genre, year, director);
        movies.add(newMovie);
        return newMovie;
    }

    public Director addDirector(String name) {
        Director newDirector = new Director(name);
        this.directors.add(newDirector);
        return newDirector;
    }

    public Genre addGenre(String genre) {
        Genre newGenre = new Genre(genre);
        this.genres.add(newGenre);
        return newGenre;
    }

    public static void addEdgeGenre(Genre startGenre, Movie endMovie) {
        startGenre.addMovieEdge(endMovie);
    }

    public static void addEdgeDirector(Director startDirector, Movie endMovie) {
        startDirector.addMovieEdge(endMovie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public ArrayList<Director> getDirectors() {
        return this.directors;
    }
    public ArrayList<Genre> getGenres() {
        return this.genres;
    }


    public Movie getMovieByGenre(String genre) {
        for(Movie m: movies) {
            if (Objects.equals(m.getGenre(), genre)) {
                return m;
            }
        }
        return null;
    }

    public void createEdgesByGenre(Genre genre){
        for (Movie m: movies){
            if (Objects.equals(m.getGenre().toLowerCase(), genre.getGenre().toLowerCase())) {
                addEdgeGenre(genre, m);
            }
        }
    }

    public void createEdgesByDirector(Director director){
        for (Movie m: movies){
            if (Objects.equals(m.getGenre().toLowerCase(), director.getName().toLowerCase())) {
                addEdgeDirector(director, m);
            }
        }
    }

    public void printMoviesByDecade(ArrayList<Movie> movies, int startYear) {
        int endYear = startYear + 9;

        System.out.println("Movies from " + startYear + "s:");

        for (Movie movie : movies) {
            if (movie.getYear() >= startYear && movie.getYear() <= endYear) {
                System.out.println(movie.getTitle());
            }
        }
    }
}