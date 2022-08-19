package com.vinsguru.graphqlplayground.lec15.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
public class HeaderCheckFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var isEmpty = exchange.getRequest().getHeaders().getOrEmpty("caller-id").isEmpty();
        return !isEmpty ? chain.filter(exchange) :
                Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST));
    }

}
