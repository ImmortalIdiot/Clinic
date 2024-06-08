package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.ClinicDB;
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
import java.util.Objects;

public class RawQueryController {

    @FXML
    private Button rawQueryBackButton;

    @FXML
    private Label rawQueryLabel;

    @FXML
    private Label error;

    @FXML
    private TextField rawQueryTextField;

    @FXML
    private Button rawQueryExecuteButton;

    @FXML
    private TableView<DataField> rawQueryTableView;

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
    void search(ActionEvent event) {
        //TODO: implement sending request
    }

    @FXML
    void initialize() {
        assert rawQueryBackButton != null : "fx:id=\"rawQueryBackButton\" was not injected: check your FXML file 'raw-query.fxml'.";
        assert rawQueryLabel != null : "fx:id=\"rawQueryLabel\" was not injected: check your FXML file 'raw-query.fxml'.";
        assert rawQueryTextField != null : "fx:id=\"rawQueryTextField\" was not injected: check your FXML file 'raw-query.fxml'.";
        assert rawQueryExecuteButton != null : "fx:id=\"rawQueryExecuteButton\" was not injected: check your FXML file 'raw-query.fxml'.";
        assert rawQueryTableView != null : "fx:id=\"rawQueryTableView\" was not injected: check your FXML file 'raw-query.fxml'.";
    }
}
