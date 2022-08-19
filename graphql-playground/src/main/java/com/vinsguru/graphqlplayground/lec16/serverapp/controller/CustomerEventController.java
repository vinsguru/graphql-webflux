package com.vinsguru.graphqlplayground.lec16.serverapp.controller;

import com.vinsguru.graphqlplayground.lec16.dto.CustomerEvent;
import com.vinsguru.graphqlplayground.lec16.serverapp.service.CustomerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerEventController {

    @Autowired
    private CustomerEventService service;

    @SubscriptionMapping
    public Flux<CustomerEvent> customerEvents(){
        return this.service.subscribe();
    }

}
