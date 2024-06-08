package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.HelloApplication;
import com.immortalidiot.clinicdb.JDBCRunner;
import com.immortalidiot.clinicdb.model.DataField;
import com.immortalidiot.clinicdb.service.DatabaseService;
import com.immortalidiot.clinicdb.writer.TableWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DigitalCopyCardController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button digitalCopyCardBackButton;

    @FXML
    private TextField digitalCopyCardTextField;

    @FXML
    private Label digitalCopyCardLabel;

    @FXML
    private Label error;

    @FXML
    private Button digitalCopyCardSearchButton;

    @FXML
    private TableView<DataField> digitalCopyCardTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void search() {
        String text = digitalCopyCardTextField.getText();

        if (text.isBlank()) {
            List<DataField> data = databaseService.getPatientsWithCards();
            error.setText("");
            TableWriter.write(digitalCopyCardTableView, data);
        } else {
            try {
                validateDigitalCopyCardText(text);
                isDigitalCopyCard(text);

                List<DataField> data = databaseService.getPatientsWithDigitalTypeCards(Boolean.parseBoolean(text));
                error.setText("");
                TableWriter.write(digitalCopyCardTableView, data);
            } catch (IllegalArgumentException e) {
                error.setText(e.getMessage());
            }
        }
    }

    private void validateDigitalCopyCardText(String text) {
        Boolean.parseBoolean(text);
    }

    private boolean isDigitalCopyCard(String text) {
        switch (text.toLowerCase()) {
            case "да", "true", "правда", "t", "yes", "y", "п" -> { return true; }
            case "нет", "false", "ложь", "f", "no", "n", "л" -> { return false; }
            default -> throw new IllegalArgumentException("Неверный формат наличия карты");
        }
    }

    @FXML
    void initialize() {
        assert digitalCopyCardBackButton != null : "fx:id=\"digitalCopyCardBackButton\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardTextField != null : "fx:id=\"digitalCopyCardTextField\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardLabel != null : "fx:id=\"digitalCopyCardLabel\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardSearchButton != null : "fx:id=\"digitalCopyCardSearchButton\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardTableView != null : "fx:id=\"digitalCopyCardTableView\" was not injected: check your FXML file 'third_table.fxml'.";
    }
}
