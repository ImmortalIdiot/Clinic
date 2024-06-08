package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.HelloApplication;
import com.immortalidiot.clinicdb.JDBCRunner;
import com.immortalidiot.clinicdb.entity.MedicalCard;
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
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class RemovePatientController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
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

            removePatient(surname, name, patronymic);
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

    private void removePatient(String surname, String name, String patronymic) {
        SessionFactory sessionFactory = JDBCRunner.SESSION_FACTORY;

        sessionFactory.inTransaction(session -> {

            try {
                String hqlSelectPatient = "FROM patients WHERE surname = :surname AND name = :name AND patronymic = :patronymic";
                Patient patient = session.createQuery(hqlSelectPatient, Patient.class)
                        .setParameter("surname", surname)
                        .setParameter("name", name)
                        .setParameter("patronymic", patronymic)
                        .uniqueResult();
                if (patient == null) throw new NullPointerException();

                String hqlSelectMedicalCard = "FROM medical_cards WHERE patients.patientId = :patientId";
                MedicalCard medicalCard = session.createQuery(hqlSelectMedicalCard, MedicalCard.class)
                        .setParameter("patientId", patient.patientId)
                        .uniqueResult();

                if (medicalCard == null) throw new NullPointerException();

                session.remove(medicalCard);
                session.remove(patient);
            } catch (IllegalArgumentException e) {
                error.setText("Такой пациент с мед картой не найден");
            }
        });
    }

    @FXML
    void initialize() {
        assert removePatientAgeBackButton != null : "fx:id=\"First_table_back\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientSurnameTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientNameTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientPatronymicTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientSurnameLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientNameLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientPatronymicLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert findAndRemoveButton != null : "fx:id=\"first_table_search_button\" was not injected: check your FXML file 'third_table.fxml'.";
        assert removePatientTableView != null : "fx:id=\"first_table_table_view\" was not injected: check your FXML file 'third_table.fxml'.";
    }
}
