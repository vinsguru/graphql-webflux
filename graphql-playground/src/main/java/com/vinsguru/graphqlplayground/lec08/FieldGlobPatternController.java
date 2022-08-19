package com.vinsguru.graphqlplayground.lec08;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FieldGlobPatternController {


    @QueryMapping
    public Object level1(DataFetchingFieldSelectionSet selectionSet){
        System.out.println(selectionSet.contains("level2"));
        System.out.println(selectionSet.contains("level2/level3"));
        System.out.println(selectionSet.contains("**/level3"));
        System.out.println(selectionSet.contains("level2/**/level5"));

        return null;
    }


}
