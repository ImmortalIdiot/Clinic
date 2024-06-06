module com.immortalidiot.clinicdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires jakarta.cdi;
    requires org.jboss.logging;
    requires jakarta.xml.bind;
    requires com.fasterxml.classmate;
    requires net.bytebuddy;
    requires java.naming;

    opens com.immortalidiot.clinicdb to javafx.fxml;
    exports com.immortalidiot.clinicdb;
}