package com.immortalidiot.clinicdb.controller;

import com.immortalidiot.clinicdb.HelloApplication;
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

import java.io.IOException;
import java.util.*;

public class MondayWorkerController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button mondayWorkerBackButton;

    @FXML
    private TextField mondayWorkerField;

    @FXML
    private Label mondayWorkerLabel;

    @FXML
    private Button mondayWorkerSearchButton;

    @FXML
    private Label error;

    @FXML
    private TableView<DataField> mondayWorkerTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void search() {
        String text = mondayWorkerField.getText();

        if (text.isBlank()) {
            List<DataField> data = databaseService.getAllMondayWorkers();
            error.setText("");
            TableWriter.write(mondayWorkerTableView, data);
        } else {
            String specialization = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
            List<String> specializations = new ArrayList<>(Arrays.asList(
                    "Терапевт",
                    "Офтальмолог",
                    "Кастро-интеролог",
                    "Кардиолог",
                    "Акушер-гинеколог"
            ));

            if (specializations.contains(specialization)) {
                List<DataField> data = databaseService.getMondayWorkers(specialization);
                error.setText("");
                TableWriter.write(mondayWorkerTableView, data);
            } else {
                error.setText("Неизвестная специальность");
            }
        }
    }

    @FXML
    void initialize() {
        assert mondayWorkerBackButton != null : "fx:id=\"mondayWorkerBackButton\" was not injected: check your FXML file 'mon-worker.fxml'.";
        assert mondayWorkerField != null : "fx:id=\"mondayWorkerField\" was not injected: check your FXML file 'mon-worker.fxml'.";
        assert mondayWorkerLabel != null : "fx:id=\"mondayWorkerLabel\" was not injected: check your FXML file 'mon-worker.fxml'.";
        assert mondayWorkerSearchButton != null : "fx:id=\"mondayWorkerSearchButton\" was not injected: check your FXML file 'mon-worker.fxml'.";
        assert mondayWorkerTableView != null : "fx:id=\"mondayWorkerTableView\" was not injected: check your FXML file 'mon-worker.fxml'.";
    }
}
