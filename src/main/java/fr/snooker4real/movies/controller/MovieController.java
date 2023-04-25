package fr.snooker4real.movies.controller;

import fr.snooker4real.movies.model.Movie;
import fr.snooker4real.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/ten")
    public ResponseEntity<Page<Movie>> getTenMovies() {
        return new ResponseEntity<>(movieService.tenMoviesForCarousel(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping(params = "limit")
    public ResponseEntity<List<Movie>> getNMovies(@RequestParam("limit") int limit){
        return new ResponseEntity<>(movieService.getMovies(limit), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovieById(@PathVariable String imdbId) {
        return new ResponseEntity<>(movieService.singleMovie(imdbId), HttpStatus.OK);
    }

    @GetMapping("/genres/{genres}")
    public ResponseEntity<List<Movie>> getMovieByGenres(@PathVariable String genres) {
        return new ResponseEntity<>(movieService.movieByGenres(genres), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {


        Movie movie1 = movieService.save(movie);
        if (movie1 != null) {
            return new ResponseEntity<>(movie1, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{imdbId}")
    public Object updateMovie(@PathVariable String imdbId, @RequestBody Movie movie) {
        Optional<Movie> movie1 = movieService.singleMovie(imdbId);
        if (movie1.isPresent()) {
            Movie movie2 = movie1.get();
            movie2.setImdbId(movie.getImdbId());
            movie2.setTitle(movie.getTitle());
            movie2.setReleaseDate(movie.getReleaseDate());
            movie2.setTrailerLink(movie.getTrailerLink());
            movie2.setPoster(movie.getPoster());
            movie2.setGenres(movie.getGenres());
            movie2.setBackdrops(movie.getBackdrops());
            movie2.setReviewIds(movie.getReviewIds());

            movieService.save(movie2);
            return new ResponseEntity<>(movie2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}