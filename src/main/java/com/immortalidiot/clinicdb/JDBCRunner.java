package com.immortalidiot.clinicdb;

import com.immortalidiot.clinicdb.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;

public class JDBCRunner {
    private static final String PROTOCOL = "jdbc:postgresql://";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL_LOCALE_NAME = "localhost/";
    private static final String DATABASE_NAME = "clinic";
    private static final String DATABASE_URL = PROTOCOL + URL_LOCALE_NAME + DATABASE_NAME;
    public static final String USER_NAME = "postgres";
    public static final String DATABASE_PASS = "password";

    public static final SessionFactory SESSION_FACTORY = new Configuration()
            .addAnnotatedClass(Cabinet.class)
            .addAnnotatedClass(Doctor.class)
            .addAnnotatedClass(MedicalCard.class)
            .addAnnotatedClass(Patient.class)
            .addAnnotatedClass(Schedule.class)
            .addAnnotatedClass(Visit.class)
            .setProperty(JAKARTA_JDBC_URL, DATABASE_URL)
            .setProperty(JAKARTA_JDBC_USER, USER_NAME)
            .setProperty(JAKARTA_JDBC_PASSWORD, DATABASE_PASS)
            .setProperty(SHOW_SQL, true)
            .setProperty(FORMAT_SQL, true)
            .setProperty(HIGHLIGHT_SQL, true)
            .buildSessionFactory();

    public static void initDatabase() {
        checkDriver();
        checkDatabase();
    }

    public static void checkDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("No JDBC driver! Connect the JDBC driver to the project according to the instructions.");
            throw new RuntimeException(e);
        }
    }

    public static void checkDatabase() {
        try {
            DriverManager.getConnection(DATABASE_URL, USER_NAME, DATABASE_PASS);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("No database! Check the database name, path to the database, or deploy a local backup copy according to the instructions");
            throw new RuntimeException(e);
        }
    }
}
