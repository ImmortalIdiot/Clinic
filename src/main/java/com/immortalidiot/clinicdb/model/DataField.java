package com.immortalidiot.clinicdb.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class DataField {
    private List<Pair<String, SimpleStringProperty>> data;

    public DataField(List<Pair<String, String>> data) { setData(data); }

    public List<Pair<String, SimpleStringProperty>> getData() { return data; }

    public void setData(List<Pair<String, String>> data) {
        this.data = data.stream()
                .map(it -> new Pair<>(it.getKey(), new SimpleStringProperty(it.getValue())))
                .collect(Collectors.toList());
    }
}
