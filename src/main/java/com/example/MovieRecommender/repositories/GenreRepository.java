package com.example.MovieRecommender.repositories;

import com.example.MovieRecommender.models.Director;
import com.example.MovieRecommender.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
