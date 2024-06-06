module com.immortalidiot.clinicdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.immortalidiot.clinicdb to javafx.fxml;
    exports com.immortalidiot.clinicdb;
}