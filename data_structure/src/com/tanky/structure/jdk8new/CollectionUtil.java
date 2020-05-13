package com.tanky.structure.jdk8new;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class CollectionUtil {

    public static <T, R> List<R> convert(List<T> list, Function<T, R> function) {

        List<R> result = new ArrayList<>();
        list.forEach(t -> result.add(function.apply(t)));
        return result;
    }

}