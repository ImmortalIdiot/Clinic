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
import java.util.List;
import java.util.Objects;

public class AddPatientController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button addPatientBackButton;

    @FXML
    private Button addPatientSearchButton;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private Label patronymicLabel;

    @FXML
    private TextField ageTextField;

    @FXML
    private Label ageLabel;

    @FXML
    private TextField genderTextField;

    @FXML
    private Label genderLabel;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TableView<DataField> addPatientTableView;

    @FXML
    private Label error;

    @FXML
    void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);
    }

    @FXML
    void addAndGetAllPatients() {
        String surname = surnameTextField.getText();
        String name = nameTextField.getText();
        String patronymic = patronymicTextField.getText();
        String age = ageTextField.getText();
        String gender = genderTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        try {
            validateInputFields(surname, name, patronymic, age, gender);
            int parsedAge = validateAge(age);
            String parsedGender = validateGender(gender);

            if (phoneNumber.isBlank()) {
                phoneNumber = "-";
            } else if (
                    phoneNumber.equalsIgnoreCase("нет") ||
                    phoneNumber.equalsIgnoreCase("отсутствует")) {
                validatePhoneNumber(phoneNumber);
            }

            addPatient(surname, name, patronymic, parsedAge, parsedGender, phoneNumber);
            List<DataField> data = databaseService.getPatients();
            error.setText("");
            TableWriter.write(addPatientTableView, data);

        } catch (IllegalArgumentException e) {
            error.setText(e.getMessage());
        }
    }

    private void validateInputFields(String surname, String name, String patronymic, String age, String gender) {
        if (surname.isBlank()) throw new IllegalArgumentException("Поле с фамилией не должно быть пустым!");
        if (name.isBlank()) throw new IllegalArgumentException("Поле с именем не должно быть пустым!");
        if (patronymic.isBlank()) throw new IllegalArgumentException("Поле с отчеством не должно быть пустым!");
        if (age.isBlank()) throw new IllegalArgumentException("Поле с возрастом не должно быть пустым!");
        if (gender.isBlank()) throw new IllegalArgumentException("Поле с полом не должно быть пустым!");
    }

    private String validateGender(String gender) {
        switch (gender.toLowerCase()) {
            case "м", "мужской", "m", "male" -> gender = "М";
            case "ж", "женский", "f", "female" -> gender = "Ж";
            default -> gender = "Unknown";
        }
        if (gender.equals("Unknown")) throw new IllegalArgumentException("Неверный ввод пола");
        return gender;
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

    private static boolean isCorrectPhoneNumber(String phoneNumber) {
        return phoneNumber.charAt(0) == '9' && phoneNumber.length() == 10;
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!isCorrectPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Неверный формат ввода номера телефона");
        }
    }

    public void addPatient(
            String surname,
            String name,
            String patronymic,
            Integer age,
            String gender,
            String phoneNumber
    ) {
        SessionFactory sessionFactory = JDBCRunner.SESSION_FACTORY;

        Patient newPatient = new Patient();
        newPatient.surname = surname;
        newPatient.name = name;
        newPatient.patronymic = patronymic;
        newPatient.age = age;
        newPatient.gender = gender;
        newPatient.phoneNumber = phoneNumber;

        sessionFactory.inTransaction(session -> session.persist(newPatient));

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.patient = newPatient;
        medicalCard.hasDigitalCopy = false;

        sessionFactory.inTransaction(session -> session.persist(medicalCard));
    }

    @FXML
    void initialize() {
        assert addPatientBackButton != null : "fx:id=\"addPatientBackButton\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert surnameTextField != null : "fx:id=\"surnameTextField\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert patronymicTextField != null : "fx:id=\"patronymicTextField\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert ageTextField != null : "fx:id=\"ageTextField\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert genderTextField != null : "fx:id=\"genderTextField\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert surnameLabel != null : "fx:id=\"surnameLabel\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert patronymicLabel != null : "fx:id=\"patronymicLabel\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert ageLabel != null : "fx:id=\"ageLabel\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert genderLabel != null : "fx:id=\"genderLabel\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert addPatientSearchButton != null : "fx:id=\"addPatientSearchButton\" was not injected: check your FXML file 'add_patient.fxml'.";
        assert addPatientTableView != null : "fx:id=\"addPatientTableView\" was not injected: check your FXML file 'add_patient.fxml'.";
    }
}
