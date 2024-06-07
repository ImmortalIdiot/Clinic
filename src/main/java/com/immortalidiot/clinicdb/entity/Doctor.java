package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @Column(name = "doctor_id", nullable = false, unique = true)
    public Integer doctor_id;

    @Column(name = "name",nullable = false)
    public String name;

    @Column(name = "surname", nullable = false)
    public String surname;

    @Column(name = "patronymic")
    public String patronymic;

    @Column(name = "specialization", nullable = false)
    public String specialization;

    @Column(name = "experience", nullable = false)
    public Integer experience;

    @Override
    public String toString() {
        return "id=" + doctor_id +
                "\t| name='" + name + '\'' +
                "\t| surname='" + surname + "\'" +
                "\t| patronymic='" + patronymic + "\'" +
                "\t| specialization='" + specialization + "\'" +
                "\t| experience='" + experience + "\'";
    }
}
