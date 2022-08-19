package com.vinsguru.graphqlplayground.lec16.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto implements CustomerResponse{

    private Integer id;
    private String name;
    private Integer age;
    private String city;

}
