package com.vinsguru.graphqlmovieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class WatchListResponse {

    private Status status;
    private List<Integer> watchList;

}
