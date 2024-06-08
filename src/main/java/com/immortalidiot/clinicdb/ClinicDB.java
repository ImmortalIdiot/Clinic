package com.immortalidiot.clinicdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClinicDB extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        JDBCRunner.initDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(ClinicDB.class.getResource("menu.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1024, 720);
        stage.setTitle("Clinic");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
