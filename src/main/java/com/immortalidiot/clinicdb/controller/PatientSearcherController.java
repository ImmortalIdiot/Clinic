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
import java.util.Locale;
import java.util.Objects;

public class PatientSearcherController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button patientSearcherBackButton;

    @FXML
    private Label patientSearcherLabel;

    @FXML
    private TextField patientSearcherTextField;

    @FXML
    private Label error;

    @FXML
    private Button patientSearcherSearchButton;

    @FXML
    private TableView<DataField> patientSearcherTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);
    }

    @FXML
    protected void search() {
        String text = patientSearcherTextField.getText();

        if (text.isBlank()) {
            List<DataField> data = databaseService.getPatients();
            error.setText("");
            TableWriter.write(patientSearcherTableView, data);
        } else {
            try {
                String gender = validateGender(text);

                List<DataField> data = databaseService.getPatientsByGender(gender);
                error.setText("");
                TableWriter.write(patientSearcherTableView, data);
            } catch (IllegalArgumentException e) {
                error.setText("Некорректный пол!");
            }
        }
    }

    private String validateGender(String gender) {
        switch (gender.toLowerCase(Locale.ROOT)) {
            case "м", "мужской", "m", "male" -> {
                return  "М";
            }
            case "ж", "женский", "f", "female" -> {
                return  "Ж";
            }
            default -> throw new IllegalArgumentException();
        }
    }

    @FXML
    void initialize() {
        assert patientSearcherBackButton != null : "fx:id=\"patientSearcherBackButton\" was not injected: check your FXML file 'patient-searcher.fxml'.";
        assert patientSearcherLabel != null : "fx:id=\"patientSearcherLabel\" was not injected: check your FXML file 'patient-searcher.fxml'.";
        assert patientSearcherTextField != null : "fx:id=\"patientSearcherTextField\" was not injected: check your FXML file 'patient-searcher.fxml'.";
        assert patientSearcherSearchButton != null : "fx:id=\"patientSearcherSearchButton\" was not injected: check your FXML file 'patient-searcher.fxml'.";
        assert patientSearcherTableView != null : "fx:id=\"patientSearcherTableView\" was not injected: check your FXML file 'patient-searcher.fxml'.";
    }
}
