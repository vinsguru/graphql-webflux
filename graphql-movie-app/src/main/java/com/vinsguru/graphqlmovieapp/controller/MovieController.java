package com.vinsguru.graphqlmovieapp.controller;

import com.vinsguru.graphqlmovieapp.client.MovieClient;
import com.vinsguru.graphqlmovieapp.dto.Customer;
import com.vinsguru.graphqlmovieapp.dto.Genre;
import com.vinsguru.graphqlmovieapp.dto.Movie;
import com.vinsguru.graphqlmovieapp.dto.WatchListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieClient client;

    @SchemaMapping(typeName = "UserProfile")
    public Flux<Movie> watchList(Customer customer){
        return this.client.moviesByIds(customer.getWatchList());
    }

    @SchemaMapping(typeName = "UserProfile")
    public Flux<Movie> recommended(Customer customer){
        return this.client.moviesByGenre(customer.getFavoriteGenre());
    }

    @SchemaMapping(typeName = "WatchListResponse")
    public Flux<Movie> watchList(WatchListResponse watchListResponse){
        return this.client.moviesByIds(watchListResponse.getWatchList());
    }

    @QueryMapping
    public Mono<Movie> movieDetails(@Argument Integer id){
        return this.client.moviesByIds(List.of(id)).next();
    }

    @QueryMapping
    public Flux<Movie> moviesByGenre(@Argument Genre genre){
        return this.client.moviesByGenre(genre);
    }

}
