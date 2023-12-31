package com.example.MovieRecommender.models.graph;

import com.example.MovieRecommender.models.Director;
import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.models.Movie;

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

    public HashMap<String, Movie> getMovies() {
        return  movies;
    }

    public HashMap<String, Director> getDirectors() {
        return  directors;
    }

    public HashMap<String, Genre> getGenres() {return  genres; }

    private  Genre getGenreByKey(String genreName) {
        return genres.get(genreName);
    }
    private Director getDirectorByKey(String directorName) {
        return directors.get(directorName);
    }

    public void addMovie(Movie movie) {
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

    public void printMoviesByGenre(String genreName) {
        Genre genre = getGenreByKey(genreName);
        for(var e: genre.getMoviesEdges().values()){
            System.out.println(e.getEnd().getString());
        }
    }
    public void printMoviesByDirector(String directorName) {
        Director director = getDirectorByKey(directorName);
        for(var e: director.getMoviesEdges().values()){
            System.out.println(e.getEnd().getString());
        }
    }

    public void printAllMovies() {
        for (HashMap.Entry<String, Movie> entry : this.movies.entrySet()) {
            String key = entry.getKey();
            Movie movie = entry.getValue();
            System.out.println(movie.getString());
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

           /* if(genresToMovies.get(genreName) != null){
                genresToMovies.get(genreName).updatePopularity();
            }

            if(directorsToMovies.get(directorName) != null){
                directorsToMovies.get(directorName).updatePopularity();
            }*/

            if(genresToMovies.get(genreName) != null && directorsToMovies.get(directorName) != null){
                for (Movie m : movies.values()) {
                    if (directorsToMovies.get(directorName).getMoviesEdges().get(m.getTitle()) != null &&
                            genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()) != null
                    ){
                        if (directorsToMovies.get(directorName).getMoviesEdges().get(m.getTitle()).getEnd().getTitle().equals(
                                genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()).getEnd().getTitle()
                        )){
                            genresToMovies.get(genreName).getMoviesEdges().get(m.getTitle()).getEnd().updatePopularity();
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

    public void addMoviesToGraph(ArrayList<Movie> movies){
        for(var movie : movies){
            addMovie(movie);
        }
    }

    public void addDirectorsToGraph(ArrayList<Director> directors){
        for(var director : directors){
            var d = addDirector(director.getName());
            createEdgesDirectorToMovie(d);
        }
    }

    public void addGenresToGraph(ArrayList<Genre> genres){
        for(var genre : genres){
            var g = addGenre(genre.getGenre());
            createEdgesGenreToMovie(g);
        }
    }

}

