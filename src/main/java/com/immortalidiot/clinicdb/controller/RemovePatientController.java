package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.HelloApplication;
import com.immortalidiot.clinicdb.JDBCRunner;
import com.immortalidiot.clinicdb.model.DataField;
import com.immortalidiot.clinicdb.service.DatabaseService;
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

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void removeAndShow() {
        //TODO: implement sending request
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
