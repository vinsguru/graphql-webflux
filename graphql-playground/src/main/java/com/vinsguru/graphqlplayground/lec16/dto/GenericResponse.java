package com.vinsguru.graphqlplayground.lec16.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.graphql.ResponseError;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class GenericResponse<T> {

    private final T data;
    private final List<ResponseError> errors;
    private final boolean dataPresent;

    public GenericResponse(T data) {
        this.data = data;
        this.errors = Collections.emptyList();
        this.dataPresent = true;
    }

    public GenericResponse(List<ResponseError> errors) {
        this.data = null;
        this.errors = errors;
        this.dataPresent = false;
    }

}
