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
    private Button patient_searcher;

    @FXML
    private Button monday_therapist;

    @FXML
    void initialize() {
        assert patient_searcher != null : "fx:id=\"patient_searcher\" was not injected: check your FXML file 'Untitled'.";
        assert monday_therapist != null : "fx:id=\"monday_therapist\" was not injected: check your FXML file 'Untitled'.";
    }

    @FXML
    protected void moveToPatientSearcher(ActionEvent event) throws IOException {
        moveToScreen(event, "patient-searcher.fxml");
    }

    @FXML
    protected void moveToMondayWorker(ActionEvent event) throws IOException {
        moveToScreen(event, "mon-worker.fxml");
    }

    @FXML
    protected void moveToDoctorsInCabinet(ActionEvent event) throws IOException {
        moveToScreen(event, "doctors-in-cabinet.fxml");
    }

    @FXML
    protected void moveToDigitalCopyCard(ActionEvent event) throws IOException {
        moveToScreen(event, "digital-copy-card.fxml");
    }

    @FXML
    protected void moveToVisitInfo(ActionEvent event) throws IOException {
        moveToScreen(event, "visit-info.fxml");
    }

    @FXML
    protected void moveToScreen(ActionEvent event, String resource) throws IOException {
        if (resource.isEmpty()) return;
        if (!resource.contains(".fxml")) return;

        Parent view = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(resource)));
        Scene viewScene = new Scene(view, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }
}
