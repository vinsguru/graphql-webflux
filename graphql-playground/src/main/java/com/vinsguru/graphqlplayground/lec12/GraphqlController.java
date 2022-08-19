package com.vinsguru.graphqlplayground.lec12;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphqlController {

    @QueryMapping
    public Mono<String> sayHello(@Argument("name") String value){
        return Mono.fromSupplier(() -> "Hello " + value);
    }

}
