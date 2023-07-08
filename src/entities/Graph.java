package entities;

import entities.vertices.Movie;
import entities.vertices.Director;
import entities.vertices.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
                    e.getEnd().getYear() + " | " + e.getEnd().getDirector() + " | " + e.getEnd().getPopularity());
        }
    }
    public void printMoviesByDirector(String directorName) {
        Director director = getDirector(directorName);
        for(EdgeDirectorToMovie e: director.getMoviesEdges().values()){
            System.out.println(" -> " + e.getEnd().getTitle() + ": " + e.getEnd().getGenre() + " | " +
                    e.getEnd().getYear() + " | " + e.getEnd().getDirector()  + " | "+ e.getEnd().getPopularity());
        }
    }

    public void printAllMovies() {
        for (HashMap.Entry<String, Movie> entry : this.movies.entrySet()) {
            String key = entry.getKey();
            Movie movie = entry.getValue();
            System.out.println(key + ": " + movie.getString());
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
                System.out.println(movie.getString());
            }
        }
    }
    public void recommender(Queue<Movie> queue, Graph graph) {
        HashMap<String, Movie> movies = graph.getMovies();
        for (Movie movie : queue) {
            String directorName = movie.getDirector();
            String genreName = movie.getGenre();

            HashMap<String, Genre> genresToMovies = graph.getGenres();
            HashMap<String, Director> directorsToMovies = graph.getDirectors();

            if(genresToMovies.get(genreName) != null){
                genresToMovies.get(genreName).setPopularity();
            }

            if(directorsToMovies.get(directorName) != null){
                directorsToMovies.get(directorName).setPopularity();
            }

            if(genresToMovies.get(genreName) != null && directorsToMovies.get(directorName) != null){
                for (Movie m : movies.values()) {
                    if (directorsToMovies.get(directorName).getMoviesEdges().get(m.getTitle()) != null &&
                            genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()) != null
                    ){
                        if (directorsToMovies.get(directorName).getMoviesEdges().get(m.getTitle()).getEnd().getTitle().equals(
                                genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()).getEnd().getTitle()
                        )){
                            genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()).getEnd().setPopularity();
                            System.out.println(genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()).getEnd().getPopularity());
                        }
                    }
                }
            }

        }
    }

    public Queue<Movie> convertHashToQueue(HashMap<String, Movie> movies){
        Queue<Movie> queue = new LinkedList<>();
        for (Movie movie : movies.values()) {
            queue.add(movie);
        }
        return queue;
    }

}

