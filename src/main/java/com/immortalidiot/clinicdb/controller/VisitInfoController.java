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
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class VisitInfoController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button visitInfoBackButton;

    @FXML
    private TextField visitInfoTextField;

    @FXML
    private Label visitInfoCardLabel;

    @FXML
    private Label error;

    @FXML
    private Button visitInfoSearchButton;

    @FXML
    private TableView<DataField> visitInfoCardTableView;

    @FXML
    public void moveToMenu(ActionEvent event) throws IOException {
        Parent firstTableView = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("menu.fxml")));
        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);

    }

    @FXML
    protected void search() {
        String text = visitInfoTextField.getText();
        if (text.isBlank()) {
            List<DataField> data = databaseService.getVisitInfo();
            error.setText("");
            TableWriter.write(visitInfoCardTableView, data);
        } else {
            String dayOfWeek;
            switch (text.toLowerCase()) {
                case "понедельник", "пн", "mon", "monday" -> dayOfWeek = "MON";
                case "вторник", "вт", "tue", "tuesday" -> dayOfWeek = "TUE";
                case "среда", "ср", "wed", "wednesday" -> dayOfWeek = "WED";
                case "пятница", "пт", "fri", "friday" -> dayOfWeek = "FRI";
                case "суббота", "сб", "sat", "saturday" -> dayOfWeek = "SAT";
                case "воскресенье", "вс", "sun", "sunday" -> dayOfWeek = "SUN";
                default -> dayOfWeek = "Неизвестный день недели";
            }

            if (!dayOfWeek.equals("Неизвестный день недели")) {
                List<DataField> data = databaseService.getVisitInfoByDayOfWeek(dayOfWeek);
                error.setText("");
                TableWriter.write(visitInfoCardTableView, data);
            } else {
                error.setText(dayOfWeek);
            }

        }
    }

    @FXML
    void initialize() {
        assert visitInfoBackButton != null : "fx:id=\"First_table_back\" was not injected: check your FXML file 'third_table.fxml'.";
        assert visitInfoTextField != null : "fx:id=\"first_table_input_place_field\" was not injected: check your FXML file 'third_table.fxml'.";
        assert visitInfoCardLabel != null : "fx:id=\"first_table_input_place_label\" was not injected: check your FXML file 'third_table.fxml'.";
        assert visitInfoSearchButton != null : "fx:id=\"first_table_search_button\" was not injected: check your FXML file 'third_table.fxml'.";
        assert visitInfoCardTableView != null : "fx:id=\"first_table_table_view\" was not injected: check your FXML file 'third_table.fxml'.";
    }
}
