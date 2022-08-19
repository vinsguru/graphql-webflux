package com.vinsguru.graphqlplayground.lec11;

import com.vinsguru.graphqlplayground.lec11.dto.Book;
import com.vinsguru.graphqlplayground.lec11.dto.Electronics;
import com.vinsguru.graphqlplayground.lec11.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Controller
public class SearchEngineController {

    List<Object> list = List.of(
            FruitDto.create("banana", LocalDate.now().plusDays(3)),
            FruitDto.create("apple", LocalDate.now().plusDays(5)),
            Electronics.create("mac book", 600, "APPLE"),
            Electronics.create("phone", 400, "SAMSUNG"),
            Book.create("java", "venkat")
    );

    @QueryMapping
    public Flux<Object> search(){
        return Mono.fromSupplier(() -> new ArrayList<>(list))
                .doOnNext(Collections::shuffle)
                .flatMapIterable(Function.identity())
                .take(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

}
