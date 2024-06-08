package com.immortalidiot.clinicdb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

public class DoctorsInCabinetController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button doctorInCabinetBackButton;

    @FXML
    private TextField doctorInCabinetTextField;

    @FXML
    private Label doctorInCabinetLabel;

    @FXML
    private Label error;

    @FXML
    private Button doctorInCabinetSearchButton;

    @FXML
    private TableView<DataField> doctorInCabinetTableView;

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
    protected void search() {
        String text = doctorInCabinetTextField.getText();

        if (text.isBlank()) {
            List<DataField> data = databaseService.getDoctorSpecializationsAndCabinets();
            error.setText("");
            TableWriter.write(doctorInCabinetTableView, data);
        } else {
            try {
                correctCabinetNumber(text);

                List<DataField> data = databaseService.getDoctorSpecializationsInCabinet(Integer.parseInt(text));
                error.setText("");
                TableWriter.write(doctorInCabinetTableView, data);
            } catch (IllegalArgumentException e) {
                error.setText("Неверный формат кабинета!");
            }
        }
    }

    private void correctCabinetNumber(String cabinet) {
        if (Integer.parseInt(cabinet) <= 0) throw new IllegalArgumentException();
    }

    @FXML
    void initialize() {
        assert doctorInCabinetBackButton != null :
                "fx:id=\"doctorInCabinetBackButton\" was not injected: check your FXML file " +
                        "'doctors-in-cabinet.fxml'.";
        assert doctorInCabinetTextField != null :
                "fx:id=\"doctorInCabinetTextField\" was not injected: check your FXML file " +
                        "'doctors-in-cabinet.fxml'.";
        assert doctorInCabinetLabel != null :
                "fx:id=\"doctorInCabinetLabel\" was not injected: check your FXML file " +
                        "'doctors-in-cabinet.fxml'.";
        assert doctorInCabinetSearchButton != null :
                "fx:id=\"doctorInCabinetSearchButton\" was not injected: check your FXML file " +
                        "'doctors-in-cabinet.fxml'.";
        assert doctorInCabinetTableView != null :
                "fx:id=\"doctorInCabinetTableView\" was not injected: check your FXML file " +
                        "'doctors-in-cabinet.fxml'.";
    }
}
