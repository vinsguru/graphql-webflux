package com.vinsguru.graphqlplayground.lec06;

import com.vinsguru.graphqlplayground.lec06.dto.CustomerWithOrder;
import com.vinsguru.graphqlplayground.lec06.service.CustomerService;
import com.vinsguru.graphqlplayground.lec06.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService service;

    @Autowired
    private OrderService orderService;

    @GetMapping("customers")
    public Flux<CustomerWithOrder> customerOrders(){
        return this.service.allCustomers()
                .flatMap(c ->
                        this.orderService.ordersByCustomerName(c.getName())
                                .collectList()
                                .map(l -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(), l))
                );
    }

}
