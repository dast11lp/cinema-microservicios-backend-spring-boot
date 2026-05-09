package com.cinema.catalog.controllers;

import com.cinema.catalog.entities.Movie;
import com.cinema.catalog.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/movies")
@CrossOrigin("*") 
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("list")
    public ResponseEntity<Page<Movie>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<Movie> movies = movieService.findAll(page, size);
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(movie);
    }
}
