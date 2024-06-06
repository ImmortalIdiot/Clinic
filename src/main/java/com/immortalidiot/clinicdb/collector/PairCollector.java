package com.immortalidiot.clinicdb.collector;

import javafx.util.Pair;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PairCollector implements Collector<Map.Entry<String, Object>, List<Pair<String, String>>, List<Pair<String, String>>> {

    @Override
    public Supplier<List<Pair<String, String>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Pair<String, String>>, Map.Entry<String, Object>> accumulator() {
        return (list, entry) -> list.add(new Pair<>(entry.getKey(), entry.getValue() == null ? "null" : entry.getValue().toString()));
    }

    @Override
    public BinaryOperator<List<Pair<String, String>>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<Pair<String, String>>, List<Pair<String, String>>> finisher() {
        return Collections::unmodifiableList;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
