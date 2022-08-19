package com.vinsguru.graphqlplayground.lec02.service;

import com.vinsguru.graphqlplayground.lec02.dto.AgeRangeFilter;
import com.vinsguru.graphqlplayground.lec02.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "sam", 20, "atlanta"),
            Customer.create(2, "jake", 10, "las vegas"),
            Customer.create(3, "mike", 15, "miami"),
            Customer.create(4, "john", 5, "houston")
    );

    public Flux<Customer> allCustomers(){
        return flux;
    }

    public Mono<Customer> customerById(Integer id){
        return flux.filter(c -> c.getId().equals(id))
                .next();
    }

    public Flux<Customer> nameContains(String name){
        return flux
                .filter(c -> c.getName().contains(name));
    }

    public Flux<Customer> withinAge(AgeRangeFilter filter){
        return flux
                .filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
    }

}
