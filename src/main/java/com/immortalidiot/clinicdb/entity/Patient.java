package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false, unique = true)
    public Integer patientId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "surname", nullable = false)
    public String surname;

    @Column(name = "patronymic")
    public String patronymic;

    @Column(name = "age", nullable = false)
    public Integer age;

    @Column(name = "gender", nullable = false)
    public String gender;

    @Column(name = "phone_number")
    public String phoneNumber;

    @Override
    public String toString() {
        return "id=" + patientId +
                "\t| name='" + name + '\'' +
                "\t| surname='" + surname + "\'" +
                "\t| patronymic='" + patronymic + "\'" +
                "\t| age='" + age + "\'" +
                "\t| gender='" + gender + "\'" +
                "\t| phoneNumber='" + phoneNumber + "\'";
    }
}
