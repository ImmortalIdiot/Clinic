package com.immortalidiot.clinicdb.service;

import org.hibernate.SessionFactory;

public class DatabaseService {
    private final SessionFactory sessionFactory;

    public DatabaseService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
