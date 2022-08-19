package com.vinsguru.graphqlplayground.lec09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Product {

    private String name;
    private Map<String, String> attributes;

}
