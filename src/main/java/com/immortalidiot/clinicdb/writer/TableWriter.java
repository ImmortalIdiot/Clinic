package com.immortalidiot.clinicdb.writer;

import com.immortalidiot.clinicdb.model.DataField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TableWriter {
    public static void write(TableView<DataField> table, List<DataField> data) {
        table.setItems(FXCollections.observableList(data));
        table.getColumns().clear();

        Set<String> descriptions = data
                .stream()
                .flatMap(it -> it.getData().stream().map(Pair::getKey))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<TableColumn<DataField, String>> columns = descriptions.stream().map(description -> {
                    TableColumn<DataField, String> column = new TableColumn<>(description);
                    column.setCellValueFactory(
                            param -> new ReadOnlyObjectWrapper<>(param.getValue().getData().stream()
                                    .filter(data1 -> data1.getKey().equals(description))
                                    .map(Pair::getValue)
                                    .findFirst().orElse(new SimpleStringProperty("none"))
                                    .get())
                    );
                    return column;
                }
        ).toList();

        table.getColumns().addAll(columns);
    }
}
