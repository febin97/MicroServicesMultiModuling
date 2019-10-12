package com.stackroute.tmdb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tmdb.model.Movie;
import com.stackroute.tmdb.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    @MockBean
    private MovieService movieService;
    @InjectMocks
    private RestController restController;

    private List<Movie> list =null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
        movie = new Movie();
        movie.setId(1);
        movie.setTitle("endgame");
        list = new ArrayList<>();
        list.add(movie);
    }


    @Test
    public void getMovies() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveMovie() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movies")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/moviesByName/endgame")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movies/"+1)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}