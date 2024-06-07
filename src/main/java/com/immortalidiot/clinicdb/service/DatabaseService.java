package com.immortalidiot.clinicdb.service;

import com.immortalidiot.clinicdb.collector.PairCollector;
import com.immortalidiot.clinicdb.model.DataField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public List<DataField> getPatients() {
        return getResponse(
                "SELECT name AS \"Имя\", " +
                "surname AS \"Фамилия\", " +
                "patronymic AS \"Отчество\", " +
                "age AS \"Возраст\", " +
                "gender AS \"Пол\", " +
                "phone_number AS \"Номер телефона\" " +
                "FROM patients"
        );
    }

    public List<DataField> getPatientsByGender(String gender) {
        return getResponse(
                "SELECT name AS \"Имя\", " +
                "surname AS \"Фамилия\", " +
                "patronymic AS \"Отчество\" " +
                "FROM patients " +
                "WHERE gender = :param",
                gender
        );
    }

    public List<DataField> getAllMondayWorkers() {
        return getResponse(
                "SELECT d.doctor_id AS \"Табельный номер\", " +
                "d.surname AS \"Фамилия\", " +
                "d.specialization AS \"Специальность\", " +
                "s.time AS \"Время приёма\", " +
                "FROM doctors d " +
                "JOIN schedule s ON d.doctor_id = s.doctor_id\n" +
                "WHERE s.day_of_week = 'MON';"
        );
    }

    public List<DataField> getMondayWorkers(String specialization) {
        return getResponse(
                "SELECT name AS \"Имя\", " +
                "surname AS \"Фамилия\", " +
                "patronymic AS \"Отчество\", " +
                "specialization AS \"Специальность\", " +
                "experience AS \"Стаж\" " +
                "FROM patients " +
                "WHERE specialization = :param " +
                "AND doctor_id IN " +
                "(SELECT doctor_id FROM public.schedule " +
                "WHERE day_of_week = 'MON');\n",
            specialization
        );
    }

    private List<DataField> getResponse(String request) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(request);
        return mapToDataField(query);
    }

    private List<DataField> getResponse(String request, String param) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(request).setParameter("param", param);
        return mapToDataField(query);
    }
}
