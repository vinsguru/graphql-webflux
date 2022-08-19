package com.vinsguru.graphqlplayground.lec04;

import com.vinsguru.graphqlplayground.lec04.dto.Customer;
import com.vinsguru.graphqlplayground.lec04.dto.CustomerOrderDto;
import com.vinsguru.graphqlplayground.lec04.service.CustomerService;
import com.vinsguru.graphqlplayground.lec04.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private OrderService orderService;

   // @QueryMapping
    @SchemaMapping(typeName = "Query")
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    // N + 1
//    @BatchMapping(typeName = "Customer")
//    public Flux<List<CustomerOrderDto>> orders(List<Customer> list){
//        System.out.println("Orders method invoked for " + list);
//        return this.orderService.ordersByCustomerNames(
//            list.stream().map(Customer::getName).collect(Collectors.toList())
//        );
//    }

    @BatchMapping(typeName = "Customer")
    public Mono<Map<Customer, List<CustomerOrderDto>>> orders(List<Customer> list){
        System.out.println("Orders method invoked for " + list);
        return this.orderService.fetchOrdersAsMap(list);
    }

}
