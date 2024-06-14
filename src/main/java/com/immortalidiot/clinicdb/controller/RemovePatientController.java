package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.ClinicDB;
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

public class RemovePatientController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button removePatientAgeBackButton;

    @FXML
    private TextField patientSurnameTextField;

    @FXML
    private Label patientSurnameLabel;

    @FXML
    private TextField patientNameTextField;

    @FXML
    private Label patientNameLabel;

    @FXML
    private TextField patientPatronymicTextField;

    @FXML
    private Label patientPatronymicLabel;

    @FXML
    private Label error;

    @FXML
    private Button findAndRemoveButton;

    @FXML
    private TableView<DataField> removePatientTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(
                Objects.requireNonNull(ClinicDB.class.getResource("menu.fxml"))
        );

        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);
    }

    @FXML
    protected void removeAndShow() {
        String surname = patientSurnameTextField.getText();
        String name = patientNameTextField.getText();
        String patronymic = patientPatronymicTextField.getText();

        try {
            validateInputFields(surname, name, patronymic);

            databaseService.removePatient(surname, name, patronymic);
            List<DataField> data = databaseService.getPatients();
            error.setText("");
            TableWriter.write(removePatientTableView, data);
        } catch (IllegalArgumentException e) {
            error.setText(e.getMessage());
        }
    }

    private void validateInputFields(String surname, String name, String patronymic) {
        if (surname.isBlank()) throw new IllegalArgumentException("Поле с фамилией не может быть пустым!");
        if (name.isBlank()) throw new IllegalArgumentException("Поле с именем не может быть пустым!");
        if (patronymic.isBlank()) throw new IllegalArgumentException("Поле с отчеством не может быть пустым!");
    }

    @FXML
    void initialize() {
        assert removePatientAgeBackButton != null :
                "fx:id=\"removePatientAgeBackButton\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert patientSurnameTextField != null :
                "fx:id=\"patientSurnameTextField\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert patientNameTextField != null :
                "fx:id=\"patientNameTextField\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert patientPatronymicTextField != null :
                "fx:id=\"patientPatronymicTextField\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert patientSurnameLabel != null :
                "fx:id=\"patientSurnameLabel\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert patientNameLabel != null :
                "fx:id=\"patientNameLabel\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert patientPatronymicLabel != null :
                "fx:id=\"patientPatronymicLabel\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert findAndRemoveButton != null :
                "fx:id=\"findAndRemoveButton\" was not injected: check your FXML file 'remove-patient.fxml'.";
        assert removePatientTableView != null :
                "fx:id=\"removePatientTableView\" was not injected: check your FXML file 'remove-patient.fxml'.";
    }
}
