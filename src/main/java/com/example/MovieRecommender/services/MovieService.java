package com.example.MovieRecommender.services;

import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.models.Movie;
import com.example.MovieRecommender.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public ArrayList<Movie> getAllMovies() {
        return (ArrayList<Movie>) movieRepository.findAll();
    }
}
