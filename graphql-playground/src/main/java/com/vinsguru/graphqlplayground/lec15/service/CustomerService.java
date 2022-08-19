package com.vinsguru.graphqlplayground.lec15.service;

import com.vinsguru.graphqlplayground.lec15.dto.CustomerDto;
import com.vinsguru.graphqlplayground.lec15.dto.DeleteResponseDto;
import com.vinsguru.graphqlplayground.lec15.dto.Status;
import com.vinsguru.graphqlplayground.lec15.repository.CustomerRepository;
import com.vinsguru.graphqlplayground.lec15.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Flux<CustomerDto> allCustomers(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> customerById(Integer id){
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> createCustomer(CustomerDto dto){
        return Mono.just(dto)
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto){
        return this.repository.findById(id)
                .map(c -> EntityDtoUtil.toEntity(id, dto))
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<DeleteResponseDto> deleteCustomer(Integer id){
        return this.repository.deleteById(id)
                .thenReturn(DeleteResponseDto.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponseDto.create(id, Status.FAILURE));
    }


}
