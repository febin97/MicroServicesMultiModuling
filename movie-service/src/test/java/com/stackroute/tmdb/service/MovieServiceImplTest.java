package com.stackroute.tmdb.service;

import com.stackroute.tmdb.controller.RestController;
import com.stackroute.tmdb.exceptions.MovieAlreadyExistException;
import com.stackroute.tmdb.exceptions.MovieNotFoundException;
import com.stackroute.tmdb.model.Movie;
import com.stackroute.tmdb.repository.MovieRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {
    @Mock
    private MovieRepo movieRepo;
    private Movie movie;

    @InjectMocks
    private MovieServiceImpl movieService;

    private List<Movie> list =null;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setId(1);
        movie.setTitle("endgame");
        list = new ArrayList<>();
        list.add(movie);
    }

    @Test
    public void findAll() {
        when(movieRepo.findAll()).thenReturn(list);
        List<Movie> movieList = movieService.findAll();
        Assert.assertEquals(list, movieList);

        verify(movieRepo, times(1)).findAll();
    }

    @Test
    public void findById() throws MovieNotFoundException {
        when(movieRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(movie));
        Movie test = movieService.findById(1);
        Assert.assertEquals(movie, test);

        verify(movieRepo, times(1)).findById(movie.getId());
    }

    @Test
    public void saveMovie() throws MovieAlreadyExistException {
        when(movieRepo.save(any())).thenReturn(movie);
        boolean test = movieService.saveMovie(movie);
        Assert.assertTrue(test);

        verify(movieRepo, times(1)).save(movie);
    }

    @Test(expected = MovieAlreadyExistException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistException {
        when(movieRepo.existsById(any())).thenReturn(true);
        boolean test = movieService.saveMovie(movie);
    }

    @Test
    public void deleteMovieTest() {
        when(movieRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(movie));
        movieService.deleteMovie(1);

        verify(movieRepo, times(1)).delete(movie);
    }

}