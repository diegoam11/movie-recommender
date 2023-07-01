package user;

import entities.vertices.Movie;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class User {
    private String username;
    private Stack<Movie> favoriteMovies;
    private Queue<Movie> watchlist;

    public User(String username) {
        this.username = username;
        this.favoriteMovies = new Stack<>();
        this.watchlist = new LinkedList<>();
    }

    public void addFavoriteMovie(Movie movie) {
        favoriteMovies.push(movie);
    }

    public void addToWatchlist(Movie movie) {
        watchlist.offer(movie);
    }

}
