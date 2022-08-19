package com.vinsguru.graphqlmovieapp.client;

import com.vinsguru.graphqlmovieapp.dto.Customer;
import com.vinsguru.graphqlmovieapp.dto.CustomerInput;
import com.vinsguru.graphqlmovieapp.dto.WatchListInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerClient {

    private final WebClient client;

    public CustomerClient(@Value("${customer.service.url}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }


    public Mono<Customer> getCustomer(Integer id){
        return this.client.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<Customer> updateCustomer(CustomerInput customerInput){
        return this.client.put()
                          .uri("{id}", customerInput.getId())
                          .bodyValue(customerInput)
                          .retrieve()
                          .bodyToMono(Customer.class);
    }

    public Mono<List<Integer>> addToWatchList(WatchListInput watchListInput){
        return this.client.post()
                          .uri("watchlist")
                          .bodyValue(watchListInput)
                          .retrieve()
                          .bodyToFlux(Integer.class)
                          .collectList();
    }


}
