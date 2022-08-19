package com.vinsguru.graphqlplayground.lec06;

import com.vinsguru.graphqlplayground.lec06.dto.CustomerWithOrder;
import com.vinsguru.graphqlplayground.lec06.service.CustomerOrderDataFetcher;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerOrderDataFetcher dataFetcher;

   // @QueryMapping
    @SchemaMapping(typeName = "Query")
    public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet){
        return this.dataFetcher.customerOrders(selectionSet);
    }

//    @SchemaMapping(typeName = "Customer")
//    public Flux<CustomerOrderDto> orders(Customer customer){
//        return this.orderService.ordersByCustomerName(customer.getName());
//    }

}
