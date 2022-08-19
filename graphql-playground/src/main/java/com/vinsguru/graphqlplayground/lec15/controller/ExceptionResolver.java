package com.vinsguru.graphqlplayground.lec15.controller;

import com.vinsguru.graphqlplayground.lec15.exceptions.ApplicationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        var ex = toApplicationException(exception);
        return Mono.just(
            List.of(
                    GraphqlErrorBuilder.newError()
                            .message(ex.getMessage())
                            .errorType(ex.getErrorType())
                            .extensions(ex.getExtensions())
                            .build()
            )
        );
    }

    private ApplicationException toApplicationException(Throwable throwable){
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException) throwable :
                new ApplicationException(ErrorType.INTERNAL_ERROR, throwable.getMessage(), Collections.emptyMap());
    }

}
