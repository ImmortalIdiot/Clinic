package com.immortalidiot.clinicdb.service;

import com.immortalidiot.clinicdb.collector.PairCollector;
import com.immortalidiot.clinicdb.model.DataField;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseService {
    private final SessionFactory sessionFactory;

    public DatabaseService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static List<DataField> mapToDataField(Query query) {
        System.out.println(query.getQueryString());
        List<Map<String, Object>> result = query
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
                .getResultList();

        result.forEach(System.out::println);

        return result.stream().map(map -> new DataField(map
                .entrySet()
                .stream()
                .collect(new PairCollector()))).collect(Collectors.toList()
        );
    }

    public void getPatients() {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT name AS \"Имя\", " +
                "surname AS \"Фамилия\"" +
                "patronymic AS \"Отчество\"" +
                "age AS \"Возраст\"" +
                "gender AS \"Пол\"" +
                "phone_number AS \"Номер телефона\"" +
                " FROM patients");
        mapToDataField(query);
    }

    public void getPatientsByGender(Character gender) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT name AS \"Имя\", " +
                            "surname AS \"Фамилия\"" +
                            "patronymic AS \"Отчество\"" +
                            " FROM patients WHERE gender = :gender")
                    .setParameter("gender", gender);
        mapToDataField(query);
    }
}
