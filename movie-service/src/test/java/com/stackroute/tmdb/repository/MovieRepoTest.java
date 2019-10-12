package com.stackroute.tmdb.repository;

import com.stackroute.tmdb.model.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepoTest {
    @Autowired
    private MovieRepo movieRepo;
    private Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setId(1);
        movie.setTitle("endgame");
    }

    @After
    public void tearDown(){
        movieRepo.deleteAll();
    }

    @Test
    public void testSaveMovie(){
        movieRepo.save(movie);
        Movie fetchUser = movieRepo.findById(movie.getId()).get();
        Assert.assertEquals(1,fetchUser.getId());
    }

    @Test
    public void testSaveMovieFailure() {
        Movie test = new Movie();
        test.setId(2);
        movieRepo.save(test);
        test = movieRepo.findById(test.getId()).get();
        Assert.assertNotSame(test, movie);
    }

    @Test
    public void testGetAllUser() {
        movieRepo.save(movie);
        List<Movie> list = movieRepo.findAll();
        Assert.assertEquals(1, list.get(0).getId());
    }

    @Test
    public void testFindById() {
        movieRepo.save(movie);
        Movie test = movieRepo.findById(movie.getId()).get();
        Assert.assertEquals(test.getId(), movie.getId());
    }

}
