package com.vinsguru.graphqlplayground.lec13.repository;

import com.vinsguru.graphqlplayground.lec13.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
