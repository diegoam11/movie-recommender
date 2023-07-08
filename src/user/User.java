package user;

import entities.vertices.Movie;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class User {
    private String username;
    private String password;
    private Stack<Movie> favoriteMovies;
    private Queue<Movie> watchlist;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.favoriteMovies = new Stack<>();
        this.watchlist = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

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
    public Queue<Movie> getWatchlist(){
        return watchlist;
    }
}
