package com.example.once_movies_api.controller;

import com.example.once_movies_api.dto.MovieDTO;
import com.example.once_movies_api.entity.MovieEntity;
import com.example.once_movies_api.exception.ResourceNotFoundException;
import com.example.once_movies_api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MovieDTO>> getMovies() {
        try {
            return ResponseEntity.ok(movieService.getMovies());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.getMovie(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/movie")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Movie created successfully")
    public ResponseEntity<MovieEntity> createMovie(@Valid @RequestBody MovieEntity movie) {
        try {
            return ResponseEntity.ok(movieService.createMovie(movie));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/movie/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Movie updated successfully")
    public ResponseEntity<MovieEntity> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieEntity movie) {
        try {
            return ResponseEntity.ok(movieService.updateMovie(id, movie));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/movie/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Movie deleted successfully")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
