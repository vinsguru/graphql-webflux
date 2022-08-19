package com.vinsguru.graphqlplayground.lec04.service;

import com.vinsguru.graphqlplayground.lec04.dto.Customer;
import com.vinsguru.graphqlplayground.lec04.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {

    private final Map<String, List<CustomerOrderDto>> map = Map.of(
            "sam", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "sam-product-1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "sam-product-2")
            ),
            "mike", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-2"),
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-3")
            )
    );

    public Flux<CustomerOrderDto> ordersByCustomerName(String name){
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }

    public Flux<List<CustomerOrderDto>> ordersByCustomerNames(List<String> names){
        return Flux.fromIterable(names)
                .flatMapSequential(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
    }

    // some source
    private Mono<List<CustomerOrderDto>> fetchOrders(String name){
        return Mono.justOrEmpty(map.get(name))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0, 500)));
    }

    public Mono<Map<Customer, List<CustomerOrderDto>>> fetchOrdersAsMap(List<Customer> customers){
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(
                        Tuple2::getT1,
                        Tuple2::getT2
                );
    }


}
