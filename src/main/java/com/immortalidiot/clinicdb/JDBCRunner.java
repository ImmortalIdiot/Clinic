package com.immortalidiot.clinicdb;

import com.immortalidiot.clinicdb.collector.PairCollector;
import com.immortalidiot.clinicdb.model.DataField;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JDBCRunner {
    private static final String PROTOCOL = "jdbc:postgresql://";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL_LOCALE_NAME = "localhost/";
    private static final String URL_REMOTE = "10.242.65.114:5432/";
    private static final String DATABASE_NAME = "clinic";

    private static final String DATABASE_URL = PROTOCOL + URL_LOCALE_NAME + DATABASE_NAME;

    public static final String USER_NAME = "postgres";
    public static final String DATABASE_PASS = "password";

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

    private static List<DataField> mapToDataField(Query query) {
        System.out.println(query.getQueryString());
        List<Map<String, Object>> result =
                query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).getResultList();

        result.forEach(System.out::println);

        return result.stream().map(map ->
                new DataField(map.entrySet().stream().collect(new PairCollector()))).collect(Collectors.toList());
    }
}
