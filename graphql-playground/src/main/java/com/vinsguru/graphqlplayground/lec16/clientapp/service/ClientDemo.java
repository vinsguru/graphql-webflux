package com.vinsguru.graphqlplayground.lec16.clientapp.service;

import com.vinsguru.graphqlplayground.lec16.clientapp.client.CustomerClient;
import com.vinsguru.graphqlplayground.lec16.clientapp.client.SubscriptionClient;
import com.vinsguru.graphqlplayground.lec16.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ClientDemo implements CommandLineRunner {

    @Autowired
    private CustomerClient client;

    @Autowired
    private SubscriptionClient subscriptionClient;

    @Override
    public void run(String... args) throws Exception {
        this.subscriptionClient
             .customerEvents()
             .doOnNext(e -> System.out.println(" ** " + e.toString() + " ** "))
             .subscribe();
        allCustomersDemo()
                .then(this.customerByIdDemo())
                .then(this.createCustomerDemo())
                .then(this.updateCustomerDemo())
                .then(this.deleteCustomerDemo())
                .subscribe();
    }

    private Mono<Void> rawQueryDemo(){
        String query = "                {\n" +
                "                   a: customers{\n" +
                "                        id\n" +
                "                        name\n" +
                "                        age\n" +
                "                        city\n" +
                "                    }\n" +
                "                }";

        Mono<List<CustomerDto>> mono = this.client.rawQuery(query)
                                                       .map(cr -> cr.field("a").toEntityList(CustomerDto.class));

        return this.executor("Raw Query", mono);
    }

    private Mono<Void> getCustomerById(){
        return this.executor("getCustomerById", this.client.getCustomerByIdWithUnion(5));
    }

    private Mono<Void> allCustomersDemo(){
        return this.executor("allCustomersDemo", this.client.allCustomers());
    }

    private Mono<Void> customerByIdDemo(){
        return this.executor("customerByIdDemo", this.client.customerById(11));
    }

    private Mono<Void> createCustomerDemo(){
        return this.executor("createCustomerDemo", this.client.createCustomer(CustomerDto.create(null, "obie", 45, "detroit")));
    }

    private Mono<Void> updateCustomerDemo(){
        return this.executor("updateCustomerDemo", this.client.updateCustomer(
                2,
                CustomerDto.create(null, "jackson", 15, "dallas"))
        );
    }

    private Mono<Void> deleteCustomerDemo(){
        return this.executor("deleteCustomerDemo", this.client.deleteCustomer(3));
    }

    private <T> Mono<Void> executor(String message, Mono<T> mono){
        return Mono.delay(Duration.ofSeconds(1))
                   .doFirst(() -> System.out.println(message))
                   .then(mono)
                   .doOnNext(System.out::println)
                   .then();
    }

}
