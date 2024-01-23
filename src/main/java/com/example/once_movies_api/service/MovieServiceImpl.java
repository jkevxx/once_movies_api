package com.example.once_movies_api.service;

import com.example.once_movies_api.dto.CategoryDTO;
import com.example.once_movies_api.dto.MovieDTO;
import com.example.once_movies_api.entity.CategoryEntity;
import com.example.once_movies_api.entity.MovieEntity;
import com.example.once_movies_api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getMovies() {
        List<MovieDTO> movies = movieRepository.findAll().stream().map(this::convertToDTO).toList();

        if (movies.isEmpty()) {
            throw new RuntimeException("Movies not found");
        }

        return movies;
    }

    @Override
    public MovieDTO getMovie(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id).orElse(null);
        if (movieEntity == null) {
            throw new RuntimeException("Movie not found");
        }

        return convertToDTO(movieEntity);
    }

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public MovieEntity updateMovie(Long id, MovieEntity movieEntity) {

        MovieEntity movieFound = movieRepository.findById(id).orElse(null);
        if (movieFound == null) {
            throw new RuntimeException("Movie not found");
        }

        movieFound.setTitle(movieEntity.getTitle());
        movieFound.setSynopsis(movieEntity.getSynopsis());
        movieFound.setReleaseDate(movieEntity.getReleaseDate());
        movieFound.setTrailerYoutube(movieEntity.getTrailerYoutube());
        updateMovieCategories(movieFound, movieEntity.getCategories());

        return movieRepository.save(movieFound);
    }

    public void updateMovieCategories(MovieEntity movie, List<CategoryEntity> categories) {

        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

        movie.getCategories().clear();

        if (categories != null && !categories.isEmpty()) {
            categories.forEach(categoryEntity -> {
                movie.getCategories().add(categoryEntity);
            });
        }
    }

    @Override
    public void deleteMovie(Long id) {
        MovieEntity movieFound = movieRepository.findById(id).orElse(null);

        if (movieFound == null) {
            throw new RuntimeException("Movie not found");
        }

        movieRepository.delete(movieFound);
    }

    private MovieDTO convertToDTO(MovieEntity movieEntity) {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setSynopsis(movieEntity.getSynopsis());
        movieDTO.setReleaseData(movieEntity.getReleaseDate());
        movieDTO.setTrailerYoutube(movieEntity.getTrailerYoutube());

        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        if (movieEntity.getCategories() != null && !movieEntity.getCategories().isEmpty()) {
            movieEntity.getCategories().forEach(categoryEntity -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setTitle(categoryEntity.getTitle());
                categoriesDTO.add(categoryDTO);
            });
        }

        movieDTO.setCategories(categoriesDTO);

        return movieDTO;
    }
}
