package com.immortalidiot.clinicdb.service;

import com.immortalidiot.clinicdb.collector.PairCollector;
import com.immortalidiot.clinicdb.model.DataField;
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
}
