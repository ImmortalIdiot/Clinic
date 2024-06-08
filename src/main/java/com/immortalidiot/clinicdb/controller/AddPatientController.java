package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.HelloApplication;
import com.immortalidiot.clinicdb.model.DataField;
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
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPatientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void search(ActionEvent event) {
        //TODO: implement sending request
    }

    @FXML
    void initialize() {
        assert addPatientBackButton != null : "fx:id=\"First_table_back\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert surnameTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert nameTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert patronymicTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert ageTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert genderTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert surnameLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert nameLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert patronymicLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert ageLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert genderLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert phoneNumberLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert addPatientSearchButton != null : "fx:id=\"first_table_search_button\" was not injected: check your FXML file 'eight_table.fxml'.";
        assert addPatientTableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'eight_table.fxml'.";
    }
}
