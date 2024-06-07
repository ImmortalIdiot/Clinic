package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @Column(name = "patient_id", nullable = false, unique = true)
    public Integer patient_id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String surname;

    @Column
    public String patronymic;

    @Column(nullable = false)
    public Integer age;

    @Column(nullable = false)
    public Character gender;

    @Column
    public String phoneNumber;

    @Override
    public String toString() {
        return "id=" + name +
                "\t| name='" + name + '\'' +
                "\t| surname='" + surname + "\'" +
                "\t| patronymic='" + patronymic + "\'" +
                "\t| age='" + age + "\'" +
                "\t| gender='" + gender + "\'" +
                "\t| phoneNumber='" + phoneNumber + "\'";
    }
}
