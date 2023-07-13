package com.example.MovieRecommender.services;

import com.example.MovieRecommender.models.Director;
import com.example.MovieRecommender.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    public ArrayList<Director> getAllDirectors() {
        return (ArrayList<Director>) directorRepository.findAll();
    }
}
