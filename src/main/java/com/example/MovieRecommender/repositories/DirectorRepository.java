package com.example.MovieRecommender.repositories;

import com.example.MovieRecommender.models.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {

}
