package com.vinsguru.graphqlplayground.lec12;

import graphql.ExecutionInput;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Configuration
public class OperationCachingConfig {

    /*
            request body
            exe doc
            parse
            validation
            exe doc

            suggestion:
            use variables along with operation mame
            prefer this: https://github.com/ben-manes/caffeine


     */

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(PreparsedDocumentProvider provider){
        return c -> c.configureGraphQl(builder -> builder.preparsedDocumentProvider(provider));
    }

    @Bean
    public PreparsedDocumentProvider provider(){
        Map<String, PreparsedDocumentEntry> map = new ConcurrentHashMap<>();
        return new PreparsedDocumentProvider() {
            @Override
            public CompletableFuture<PreparsedDocumentEntry> getDocumentAsync(ExecutionInput executionInput, Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
                var documentEntry = map.computeIfAbsent(executionInput.getQuery(), key -> {
                    System.out.println("Not found : " + key);
                    var r = parseAndValidateFunction.apply(executionInput);
                    System.out.println("Caching : " + r);
                    return r;
                });
                return CompletableFuture.completedFuture(documentEntry);
            }
        };
    }


}
