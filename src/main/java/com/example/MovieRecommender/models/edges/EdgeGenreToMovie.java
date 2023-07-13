package com.example.MovieRecommender.models.edges;

import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.models.Movie;

public class EdgeGenreToMovie {
    private Genre start;
    private Movie end;

    public EdgeGenreToMovie(Genre startGenre, Movie endMovie) {
        this.start = startGenre;
        this.end = endMovie;
    }

    public Genre getStart() {
        return this.start;
    }
    public Movie getEnd() { return this.end; }

}
