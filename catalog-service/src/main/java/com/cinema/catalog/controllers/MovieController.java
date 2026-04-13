package com.cinema.catalog.controllers;

import com.cinema.catalog.entities.Movie;
import com.cinema.catalog.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin("*") 
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = movieService.findAll();
        
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        List<Movie> datos = movies.stream()
            .map(m -> new Movie())
            .toList();

        return ResponseEntity.ok(datos);
    }
}
