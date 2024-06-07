package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    private Button one_table;

    @FXML
    private Button monday_therapist;

    @FXML
    void initialize() {
        assert one_table != null : "fx:id=\"one_table\" was not injected: check your FXML file 'Untitled'.";
        assert monday_therapist != null : "fx:id=\"monday_therapist\" was not injected: check your FXML file 'Untitled'.";
    }

    @FXML
    protected void moveToPatientSearcher(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("patient-searcher.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(firstTableViewScene);
    }

    @FXML
    protected void moveToMondayWorker(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("mon-worker.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(firstTableViewScene);
    }
}
