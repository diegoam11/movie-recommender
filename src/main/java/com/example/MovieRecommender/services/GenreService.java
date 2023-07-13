package com.example.MovieRecommender.services;

import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public ArrayList<Genre> getAllGenres() {
        return (ArrayList<Genre>) genreRepository.findAll();
    }
}
