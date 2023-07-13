package com.example.MovieRecommender.models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class User {
    private Stack<Movie> favoriteMovies;
    private Queue<Movie> watchlist;

    public User() {
        this.favoriteMovies = new Stack<>();
        this.watchlist = new LinkedList<>();
    }
    public void addFavoriteMovie(Movie movie) {
        this.favoriteMovies.push(movie);
    }

    public void addToWatchlist(Movie movie) {
        this.watchlist.offer(movie);
    }
    public Queue<Movie> getWatchlist(){
        return this.watchlist;
    }
    public Stack<Movie> getFavoriteMovies(){
        return this.favoriteMovies;
    }
}
