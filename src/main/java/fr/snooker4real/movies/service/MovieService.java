package fr.snooker4real.movies.service;

import fr.snooker4real.movies.repository.MovieRepository;
import fr.snooker4real.movies.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMovie(String imDbId) {
        return movieRepository.findMovieByImdbId(imDbId);
    }


}
