package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.ClinicDB;
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
    private Button patientSearcher;

    @FXML
    private Button mondayTherapist;

    @FXML
    private Button doctorsInCabinets;

    @FXML
    private Button digitalCopyCard;

    @FXML
    private Button visitInfo;

    @FXML
    private Button addPatient;

    @FXML
    private Button editAge;

    @FXML
    private Button removePatient;

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
    protected void moveToAddPatient(ActionEvent event) throws IOException {
        moveToScreen(event, "add-patient.fxml");
    }

    @FXML
    protected void moveToEditAge(ActionEvent event) throws IOException {
        moveToScreen(event, "edit-age.fxml");
    }

    @FXML
    protected void moveToRemovePatient(ActionEvent event) throws IOException {
        moveToScreen(event, "remove-patient.fxml");
    }

    @FXML
    protected void moveToScreen(ActionEvent event, String resource) throws IOException {
        if (resource.isEmpty()) return;
        if (!resource.contains(".fxml")) return;

        Parent view = FXMLLoader.load(
                Objects.requireNonNull(ClinicDB.class.getResource(resource))
        );

        Scene viewScene = new Scene(view, 1024, 720);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    @FXML
    void initialize() {
        assert patientSearcher != null :
                "fx:id=\"patientSearcher\" was not injected: check your FXML file 'menu.fxml'.";
        assert mondayTherapist != null :
                "fx:id=\"mondayTherapist\" was not injected: check your FXML file 'menu.fxml'.";
        assert doctorsInCabinets != null :
                "fx:id=\"doctorsInCabinets\" was not injected: check your FXML file 'menu.fxml'.";
        assert digitalCopyCard != null :
                "fx:id=\"digitalCopyCard\" was not injected: check your FXML file 'menu.fxml'.";
        assert visitInfo != null :
                "fx:id=\"visitInfo\" was not injected: check your FXML file 'menu.fxml'.";
        assert addPatient != null :
                "fx:id=\"addPatient\" was not injected: check your FXML file 'menu.fxml'.";
        assert editAge != null :
                "fx:id=\"editAge\" was not injected: check your FXML file 'menu.fxml'.";
        assert removePatient != null :
                "fx:id=\"removePatient\" was not injected: check your FXML file 'menu.fxml'.";
    }
}
