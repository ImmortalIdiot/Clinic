package com.immortalidiot.clinicdb.controller;

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

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class VisitInfoController {

    private final DatabaseService databaseService = new DatabaseService(JDBCRunner.SESSION_FACTORY);

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
        Parent firstTableView = FXMLLoader.load(
                Objects.requireNonNull(ClinicDB.class.getResource("menu.fxml"))
        );

        Scene firstTableViewScene = new Scene(firstTableView, 1024, 720);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(firstTableViewScene);
    }

    @FXML
    protected void search() {
        String dayOfWeek = visitInfoTextField.getText();

        if (dayOfWeek.isBlank()) {
            List<DataField> data = databaseService.getVisitInfo();
            error.setText("");
            TableWriter.write(visitInfoCardTableView, data);
        } else {
            try {
                dayOfWeek = validateDayOfWeek(dayOfWeek);

                List<DataField> data = databaseService.getVisitInfoByDayOfWeek(dayOfWeek);
                error.setText("");
                TableWriter.write(visitInfoCardTableView, data);
            } catch (IllegalArgumentException e) {
                error.setText("Неверный формат дня недели");
            }
        }
    }

    private String validateDayOfWeek(String dayOfWeek) {
        switch (dayOfWeek.toLowerCase()) {
            case "понедельник", "пн", "mon", "monday" -> {
                return  "MON";
            }
            case "вторник", "вт", "tue", "tuesday" -> {
                return  "TUE";
            }
            case "среда", "ср", "wed", "wednesday" -> {
                return  "WED";
            }
            case "пятница", "пт", "fri", "friday" -> {
                return  "FRI";
            }
            case "суббота", "сб", "sat", "saturday" -> {
                return  "SAT";
            }
            case "воскресенье", "вс", "sun", "sunday" -> {
                return  "SUN";
            }
            default -> throw new IllegalArgumentException();
        }
    }

    @FXML
    void initialize() {
        assert visitInfoBackButton != null :
                "fx:id=\"visitInfoBackButton\" was not injected: check your FXML file 'visit-info.fxml'.";
        assert visitInfoTextField != null :
                "fx:id=\"visitInfoTextField\" was not injected: check your FXML file 'visit-info.fxml'.";
        assert visitInfoCardLabel != null :
                "fx:id=\"visitInfoCardLabel\" was not injected: check your FXML file 'visit-info.fxml'.";
        assert visitInfoSearchButton != null :
                "fx:id=\"visitInfoSearchButton\" was not injected: check your FXML file 'visit-info.fxml'.";
        assert visitInfoCardTableView != null :
                "fx:id=\"visitInfoCardTableView\" was not injected: check your FXML file 'visit-info.fxml'.";
    }
}
