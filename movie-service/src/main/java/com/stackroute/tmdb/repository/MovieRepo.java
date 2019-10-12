package com.stackroute.tmdb.repository;

import com.stackroute.tmdb.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends MongoRepository<Movie, Integer> {
    List<Movie> findByTitleIgnoreCaseContaining(String title);
}
