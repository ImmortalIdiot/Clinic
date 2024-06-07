package com.immortalidiot.clinicdb.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

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

public class DoctorsInCabinetController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button First_table_back;

    @FXML
    private TextField textField;

    @FXML
    private Label first_table_input_place_label;

    @FXML
    private Label error;

    @FXML
    private Button first_table_search_button;

    @FXML
    private TableView<DataField> tableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void search() {
        String text = textField.getText();

        if (text.isBlank()) {
            List<DataField> data = databaseService.getDoctorSpecializationsAndCabinets();
            error.setText("");
            TableWriter.write(tableView, data);
        } else {
            try {
                List<DataField> data = databaseService.getDoctorSpecializationsInCabinet(Integer.parseInt(text));
                error.setText("");
                TableWriter.write(tableView, data);
            } catch (NumberFormatException e) {
                error.setText(e.getMessage());
            }
        }
    }

    @FXML
    void initialize() {
        assert First_table_back != null : "fx:id=\"First_table_back\" was not injected: check your FXML file 'third_table.fxml'.";
        assert textField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert first_table_input_place_label != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert first_table_search_button != null : "fx:id=\"first_table_search_button\" was not injected: check your FXML file 'third_table.fxml'.";
        assert tableView != null : "fx:id=\"first_table_table_view\" was not injected: check your FXML file 'third_table.fxml'.";

    }

}
