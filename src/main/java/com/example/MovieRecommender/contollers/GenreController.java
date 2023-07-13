package com.example.MovieRecommender.contollers;

import com.example.MovieRecommender.models.Director;
import com.example.MovieRecommender.models.Genre;
import com.example.MovieRecommender.services.DirectorService;
import com.example.MovieRecommender.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping()
    public ArrayList<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }
}
