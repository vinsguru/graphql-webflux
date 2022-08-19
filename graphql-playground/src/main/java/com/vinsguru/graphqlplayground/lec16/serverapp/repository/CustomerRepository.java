package com.vinsguru.graphqlplayground.lec16.serverapp.repository;

import com.vinsguru.graphqlplayground.lec16.serverapp.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
