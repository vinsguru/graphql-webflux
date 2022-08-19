package com.vinsguru.graphqlplayground.lec10;

import com.vinsguru.graphqlplayground.lec10.dto.Book;
import com.vinsguru.graphqlplayground.lec10.dto.Electronics;
import com.vinsguru.graphqlplayground.lec10.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products(){
        return Flux.just(
                FruitDto.create("banana", 1, LocalDate.now().plusDays(3)),
                FruitDto.create("apple", 2, LocalDate.now().plusDays(5)),
                Electronics.create("mac book", 600, "APPLE"),
                Electronics.create("phone", 400, "SAMSUNG"),
                Book.create("java", 40, "venkat")
        );
    }

}
