package com.vinsguru.graphqlmovieapp.controller;

import com.vinsguru.graphqlmovieapp.client.ReviewClient;
import com.vinsguru.graphqlmovieapp.dto.Movie;
import com.vinsguru.graphqlmovieapp.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class ReviewController {

    @Autowired
    private ReviewClient client;

    @SchemaMapping(typeName = "MovieDetails")
    public Flux<Review> reviews(Movie movie){
        return this.client.reviews(movie.getId());
    }

}
