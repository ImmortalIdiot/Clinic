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
import java.util.List;
import java.util.Objects;

public class DigitalCopyCardController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private Button digitalCopyCardBackButton;

    @FXML
    private TextField digitalCopyCardTextField;

    @FXML
    private Label digitalCopyCardLabel;

    @FXML
    private Label error;

    @FXML
    private Button digitalCopyCardSearchButton;

    @FXML
    private TableView<DataField> digitalCopyCardTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void search() {
        String text = digitalCopyCardTextField.getText();

        if (text.isBlank()) {
            List<DataField> data = databaseService.getPatientsWithCards();
            error.setText("");
            TableWriter.write(digitalCopyCardTableView, data);
        } else {
            boolean isDigitalCopy;
            switch (text.toLowerCase()) {
                case "да", "true", "правда", "t", "yes", "y", "п" ->  isDigitalCopy = true;
                default -> isDigitalCopy = false;
            }

            List<DataField> data = databaseService.getPatientsWithDigitalTypeCards(isDigitalCopy);
            error.setText("");
            TableWriter.write(digitalCopyCardTableView, data);
        }
    }

    @FXML
    void initialize() {
        assert digitalCopyCardBackButton != null : "fx:id=\"First_table_back\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardSearchButton != null : "fx:id=\"first_table_search_button\" was not injected: check your FXML file 'third_table.fxml'.";
        assert digitalCopyCardTableView != null : "fx:id=\"first_table_table_view\" was not injected: check your FXML file 'third_table.fxml'.";
    }
}
