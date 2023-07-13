package com.example.MovieRecommender.contollers;

import com.example.MovieRecommender.models.Director;
import com.example.MovieRecommender.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    @Autowired
    DirectorService directorService;

    @GetMapping()
    public ArrayList<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }
}
