package com.vinsguru.graphqlplayground.lec07.service;

import com.vinsguru.graphqlplayground.lec07.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "sam", 20, "atlanta"),
            Customer.create(2, "jake", 10, "las vegas"),
            Customer.create(3, "mike", 15, "miami"),
            Customer.create(4, "john", 5, "houston")
    );

    public Flux<Customer> allCustomers(){
        return flux.delayElements(Duration.ofSeconds(1))
                .doOnNext(c -> print("customer " + c.getName()));
    }

    private void print(String msg){
        System.out.println(LocalDateTime.now() + " : " + msg);
    }

}
