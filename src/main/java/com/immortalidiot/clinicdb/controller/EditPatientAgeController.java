package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.ClinicDB;
import com.immortalidiot.clinicdb.JDBCRunner;
import com.immortalidiot.clinicdb.entity.Patient;
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
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EditPatientAgeController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button correctPatientAgeBackButton;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private Label patientIdLabel;

    @FXML
    private TextField newAgeTextField;

    @FXML
    private Label newAgeLabel;

    @FXML
    private Label error;

    @FXML
    private Button findAndReplaceButton;

    @FXML
    private TableView<DataField> correctAgeTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(
                Objects.requireNonNull(ClinicDB.class.getResource("menu.fxml"))
        );

        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);
    }

    @FXML
    protected void editAndShow() {
        String textId = patientIdTextField.getText();
        String age = newAgeTextField.getText();

        try {
            validateInputFields(textId, age);
            int parsedId = validateId(textId);
            int parsedAge = validateAge(age);

            editPatientAge(parsedId, parsedAge);
            List<DataField> data = databaseService.getPatients();
            error.setText("");
            TableWriter.write(correctAgeTableView, data);

        } catch (IllegalArgumentException e) {
            error.setText(e.getMessage());
        }
    }

    private void validateInputFields(String id, String age) {
        if (id.isBlank()) throw new IllegalArgumentException("Поле с номером пациента не должно быть пустым!");
        if (age.isBlank()) throw new IllegalArgumentException("Поле с возрастом не должно быть пустым!");
    }

    private Integer validateId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер пациента должен быть натуральным числом");
        }
    }

    private int validateAge(String age) {
        try {
            int parsedAge = Integer.parseInt(age);
            if (parsedAge <= 0) {
                throw new IllegalArgumentException("Возраст должен быть натуральным числом!");
            }
            return parsedAge;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Возраст должен быть числом!");
        }
    }

    private void editPatientAge(Integer id, Integer newAge) {
        SessionFactory sessionFactory = JDBCRunner.SESSION_FACTORY;
        sessionFactory.inTransaction(session -> {
            Patient patient = session.get(Patient.class, id);
            patient.age = newAge;
            session.persist(patient);
        });
    }

    @FXML
    void initialize() {
        assert correctPatientAgeBackButton != null :
                "fx:id=\"correctPatientAgeBackButton\" was not injected: check your FXML file 'edit-age.fxml'.";
        assert patientIdTextField != null :
                "fx:id=\"patientIdTextField\" was not injected: check your FXML file 'edit-age.fxml'.";
        assert newAgeTextField != null :
                "fx:id=\"newAgeTextField\" was not injected: check your FXML file 'edit-age.fxml'.";
        assert patientIdLabel != null :
                "fx:id=\"patientIdLabel\" was not injected: check your FXML file 'edit-age.fxml'.";
        assert newAgeLabel != null :
                "fx:id=\"newAgeLabel\" was not injected: check your FXML file 'edit-age.fxml'.";
        assert findAndReplaceButton != null :
                "fx:id=\"findAndReplaceButton\" was not injected: check your FXML file 'edit-age.fxml'.";
        assert correctAgeTableView != null :
                "fx:id=\"correctAgeTableView\" was not injected: check your FXML file 'edit-age.fxml'.";
    }
}
