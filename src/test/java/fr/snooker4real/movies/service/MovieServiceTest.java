package fr.snooker4real.movies.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.snooker4real.movies.repository.MovieRepository;
import fr.snooker4real.movies.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAllMovies() {
        // create some test data
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(new ObjectId(), "imdbId1", "title1", "2023-02-03", "trailerLink1", "poster1", null, null, null));
        movies.add(new Movie(new ObjectId(), "imdbId2", "title2", "2023-02-03", "trailerLink2", "poster2", null, null, null));
        movies.add(new Movie(new ObjectId(), "imdbId3", "title3", "2023-02-03", "trailerLink3", "poster3", null, null, null));

        // mock the movieRepository.findAll() method to return the test data
        when(movieRepository.findAll()).thenReturn(movies);

        // call the service method
        List<Movie> result = movieService.allMovies();

        // verify the result
        assert result.size() == movies.size();
        assert result.get(0).getTitle().equals("title1");
        assert result.get(1).getTitle().equals("title2");
        assert result.get(2).getTitle().equals("title3");

        log.info("result.get(0).getTitle() = " + result.get(0).getTitle());
        log.info("result.get(1).getTitle() = " + result.get(1).getTitle());
        log.info("result.get(2).getTitle() = " + result.get(2).getTitle());
    }

    @Test
    public void testSingleMovie() {
        // create some test data
        Movie movie = new Movie(new ObjectId(), "imdbId1", "title1", "2023-02-03", "trailerLink1", "poster1", null, null, null);

        // mock the movieRepository.findMovieByImdbId() method to return the test data
        when(movieRepository.findMovieByImdbId("imdbId1")).thenReturn(Optional.of(movie));

        // call the service method
        Optional<Movie> result = movieService.singleMovie("imdbId1");

        // verify the result
        assert result.isPresent();
        assert result.get().getTitle().equals("title1");

        log.info("result.get().getTitle() = " + result.get().getTitle());
    }
}

