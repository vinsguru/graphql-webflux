package com.vinsguru.graphqlmovieapp.dto;

import lombok.Data;

@Data
public class Movie {

    private Integer id;
    private String title;
    private Integer releaseYear;
    private Double rating;
    private Genre genre;

}
