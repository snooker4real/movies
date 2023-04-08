package fr.snooker4real.movies.controller;

import fr.snooker4real.movies.model.Movie;
import fr.snooker4real.movies.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        // create some test data
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(new ObjectId(), "imdbId1", "title1", "2023-02-03", "trailerLink1", "poster1", null, null, null));
        movies.add(new Movie(new ObjectId(), "imdbId2", "title2", "2023-02-03", "trailerLink2", "poster2", null, null, null));
        movies.add(new Movie(new ObjectId(), "imdbId3", "title3", "2023-02-03", "trailerLink3", "poster3", null, null, null));

        // mock the movieService.allMovies() method to return the test data
        when(movieService.allMovies()).thenReturn(movies);

        // call the controller method
        ResponseEntity<List<Movie>> responseEntity = movieController.getAllMovies();

        // verify the response
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody().size() == movies.size();
        assert responseEntity.getBody().get(0).getTitle().equals("title1");
        assert responseEntity.getBody().get(1).getTitle().equals("title2");
        assert responseEntity.getBody().get(2).getTitle().equals("title3");

        log.info("responseEntity.getBody().get(0).getTitle() = " + responseEntity.getBody().get(0).getTitle());
        log.info("responseEntity.getBody().get(1).getTitle() = " + responseEntity.getBody().get(1).getTitle());
        log.info("responseEntity.getBody().get(2).getTitle() = " + responseEntity.getBody().get(2).getTitle());


    }

    @Test
    public void testGetSingleMovieById() {
        // create some test data
        Movie movie = new Movie(new ObjectId(), "imdbId1", "title1", "2023-02-03", "trailerLink1", "poster1", null, null, null);

        // mock the movieService.singleMovie() method to return the test data
        when(movieService.singleMovie("imdbId1")).thenReturn(Optional.of(movie));

        // call the controller method
        ResponseEntity<Optional<Movie>> responseEntity = movieController.getSingleMovieById("imdbId1");

        // verify the response
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody().isPresent();
        assert responseEntity.getBody().get().getTitle().equals("title1");

        log.info("responseEntity.getBody().get().getTitle() = " + responseEntity.getBody().get().getTitle());
    }
}
