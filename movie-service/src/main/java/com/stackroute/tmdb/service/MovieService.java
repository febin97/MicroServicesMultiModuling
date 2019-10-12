package com.stackroute.tmdb.service;

import com.stackroute.tmdb.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(int id);
    boolean saveMovie(Movie movie);
    boolean deleteMovie(int id);
    boolean existsById(int id);
    List<Movie> getMovieByName(String name);
    void updateMovie(Movie movie);
}
