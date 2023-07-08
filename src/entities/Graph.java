package entities;

import entities.vertices.Movie;
import entities.vertices.Director;
import entities.vertices.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Graph {

    private HashMap<String, Movie> movies;
    private HashMap<String, Genre> genres;
    private HashMap<String, Director> directors;
    private boolean isDirected;

    public Graph(boolean inputIsDirected) {
        this.movies = new HashMap<>();
        this.genres = new HashMap<>();
        this.directors = new HashMap<>();
        this.isDirected = inputIsDirected;
    }

    public void addMovie(String title, String genre, int year, String director) {
        Movie movie = new Movie(title, genre, year, director);
        movies.put(movie.getTitle(), movie);
    }

    public Director addDirector(String name) {
        Director director = new Director(name);
        directors.put(director.getName(), director);
        return director;
    }

    public Genre addGenre(String genreName) {
        Genre genre = new Genre(genreName);
        genres.put(genre.getGenre(), genre);
        return genre;
    }

    public void addEdgeGenre(Genre startGenre, Movie endMovie) {
        startGenre.addMovieEdge(endMovie.getTitle(),endMovie);
    }

    public void addEdgeDirector(Director startDirector, Movie endMovie) {
        startDirector.addMovieEdge(endMovie.getTitle(), endMovie);
    }
    public HashMap<String, Movie> getMovies() {
        return  movies;
    }

    public HashMap<String, Director> getDirectors() {
        return  directors;
    }

    public HashMap<String, Genre> getGenres() {return  genres; }

    private  Genre getGenre(String genreName) {
        return genres.get(genreName);
    }
    private Director getDirector(String directorName) {
        return directors.get(directorName);
    }

    public void printMoviesByGenre(String genreName) {
        Genre genre = getGenre(genreName);
        for(EdgeGenreToMovie e: genre.getMoviesEdges().values()){
            System.out.println(" -> " + e.getEnd().getTitle() + ": " + e.getEnd().getGenre() + " | " +
                    e.getEnd().getYear() + " | " + e.getEnd().getDirector());
        }
    }
    public void printMoviesByDirector(String directorName) {
        Director director = getDirector(directorName);
        for(EdgeDirectorToMovie e: director.getMoviesEdges().values()){
            System.out.println(" -> " + e.getEnd().getTitle() + ": " + e.getEnd().getGenre() + " | " +
                    e.getEnd().getYear() + " | " + e.getEnd().getDirector());
        }
    }

    public void createEdgesGenreToMovie(Genre genre) {
        for (Movie m : movies.values()) {
            if (m.getGenre().equalsIgnoreCase(genre.getGenre())) {
                addEdgeGenre(genre, m);
            }
        }
    }

    public void createEdgesDirectorToMovie(Director director) {
        for (Movie movie : movies.values()) {
            if (movie.getDirector().equalsIgnoreCase(director.getName())) {
                addEdgeDirector(director, movie);
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
    public static void recommender(Queue<Movie> queue, Graph graph) {
        for (Movie movie : queue) {
            String directorName = movie.getDirector();
            String genreName = movie.getGenre();

            HashMap<String, Genre> moviesFromGenre = graph.getGenres();
            HashMap<String, EdgeGenreToMovie> movies =  moviesFromGenre.get(genreName).getMoviesEdges();

            HashMap<String, Director> moviesFromDirector = graph.getDirectors();
            HashMap<String, EdgeDirectorToMovie> directors =  moviesFromDirector.get(directorName).getMoviesEdges();

            if(movies.get(genreName) != null){
                movies.get(genreName).getStart().setPopularity();
            }

            if(directors.get(directorName) != null){
                directors.get(directorName).getStart().setPopularity();
            }
        }
    }

}

