package com.stackroute.tmdb.controller;

import com.stackroute.tmdb.exceptions.MovieAlreadyExistException;
import com.stackroute.tmdb.exceptions.MovieNotFoundException;
import com.stackroute.tmdb.model.Movie;
import com.stackroute.tmdb.service.MovieService;
import com.stackroute.tmdb.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
@RefreshScope
public class RestController {

    private MovieService movieService;

    @Value("${msg:Hello world - Config Server is not working..pelase check}")
    private String msg;

    @RequestMapping("/msg")
    String getMsg() {
        return this.msg;
    }

    @Autowired
    public RestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity getMovies(@RequestParam(name="name", defaultValue = "") String movieName) {
        if(!movieName.equals("")) {
            return ResponseEntity.ok(movieService.getMovieByName(movieName));
        }
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getMovie(@PathVariable(value = "id") String id) throws MovieNotFoundException {
            int movieId = Integer.parseInt(id);
            Movie movie = movieService.findById(movieId);
            return ResponseEntity.ok(movie);
    }

    @GetMapping("/moviesByName/{name}")
    public ResponseEntity getMovieByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(movieService.getMovieByName(name));
    }

    @PostMapping("/movies")
    public ResponseEntity createMovie(@Valid @RequestBody Movie movie) throws MovieAlreadyExistException {
        movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @PutMapping("/movies")
    public ResponseEntity updateMovie(@Valid @RequestBody Movie movie) {
        if(movieService.existsById(movie.getId())) {
            movieService.updateMovie(movie);
            return ResponseEntity.status(HttpStatus.OK).body(movie);
        }
        else {
            movieService.saveMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteMovie(@PathVariable(value = "id") String id) throws MovieNotFoundException{
        try {
            int movieId = Integer.parseInt(id);
            movieService.deleteMovie(movieId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (MovieNotFoundException e) {
            throw new MovieNotFoundException("Movie "+id+" not found");
        }
    }

}
