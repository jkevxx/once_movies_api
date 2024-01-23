package com.example.once_movies_api.service;

import com.example.once_movies_api.dto.MovieDTO;
import com.example.once_movies_api.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    List<MovieDTO> getMovies();

    MovieDTO getMovie(Long id);

    MovieEntity createMovie(MovieEntity movieEntity);

    MovieEntity updateMovie(Long id, MovieEntity movieEntity);

    void deleteMovie(Long id);
}
