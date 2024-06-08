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

public class EditPatientAgeController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void editAndShow() {
        //TODO: implement sending request
    }

    @FXML
    void initialize() {
        assert correctPatientAgeBackButton != null : "fx:id=\"First_table_back\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientIdTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert newAgeTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert patientIdLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert newAgeLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert findAndReplaceButton != null : "fx:id=\"first_table_search_button\" was not injected: check your FXML file 'third_table.fxml'.";
        assert correctAgeTableView != null : "fx:id=\"first_table_table_view\" was not injected: check your FXML file 'third_table.fxml'.";
    }
}
