package com.vinsguru.graphqlplayground.lec07.config;

import com.vinsguru.graphqlplayground.lec07.service.CustomerOrderDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class DataFetcherWiringConfig {

    @Autowired
    private CustomerOrderDataFetcher dataFetcher;

    @Bean
    public RuntimeWiringConfigurer configurer(){
        return c -> c.type("Query", b -> b.dataFetcher("customers", dataFetcher));
    }

}
