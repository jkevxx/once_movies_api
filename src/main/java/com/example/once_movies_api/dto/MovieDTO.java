package com.example.once_movies_api.dto;

import com.example.once_movies_api.entity.CategoryEntity;
import com.example.once_movies_api.entity.MovieEntity;

import java.time.LocalDate;
import java.util.List;

public class MovieDTO {

    private String title;
    private String synopsis;
    private LocalDate releaseData;
    private String trailerYoutube;
    private List<CategoryDTO> categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(LocalDate releaseData) {
        this.releaseData = releaseData;
    }

    public String getTrailerYoutube() {
        return trailerYoutube;
    }

    public void setTrailerYoutube(String trailerYoutube) {
        this.trailerYoutube = trailerYoutube;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
