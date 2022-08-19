package com.vinsguru.graphqlplayground.lec02;

import com.vinsguru.graphqlplayground.lec02.dto.AgeRangeFilter;
import com.vinsguru.graphqlplayground.lec02.dto.Customer;
import com.vinsguru.graphqlplayground.lec02.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    @QueryMapping
    public Mono<Customer> customerById(@Argument Integer id){
        return this.service.customerById(id);
    }

    @QueryMapping
    public Flux<Customer> customersNameContains(@Argument String name){
        return this.service.nameContains(name);
    }

    @QueryMapping
    public Flux<Customer> customersByAgeRange(@Argument AgeRangeFilter filter){
        return this.service.withinAge(filter);
    }

}
