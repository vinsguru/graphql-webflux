package com.vinsguru.graphqlplayground.lec11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class FruitDto {

    private String description;
    private LocalDate expiryDate;

}
