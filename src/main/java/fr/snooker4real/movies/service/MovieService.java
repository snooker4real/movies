package fr.snooker4real.movies.service;

import fr.snooker4real.movies.repository.MovieRepository;
import fr.snooker4real.movies.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Movie> getMovies(int limit){
        Pageable pageable = PageRequest.of(0, limit);
        return movieRepository.findTopNBy(pageable);
    }

    public Page<Movie> tenMoviesForCarousel() {
        int carousel = 10;
        return movieRepository.findAll(
                PageRequest.of(0, carousel, Sort.by("imdbRating").descending())
        );
    }

    public List<Movie> movieByGenres(String genres) {
        return movieRepository.findMovieByGenres(genres);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
}
