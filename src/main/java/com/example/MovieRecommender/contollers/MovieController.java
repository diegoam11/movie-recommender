package com.example.MovieRecommender.contollers;

import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.models.Movie;
import com.example.MovieRecommender.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping()
    public ArrayList<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
