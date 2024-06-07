package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @Column(name = "visit_id", nullable = false, unique = true)
    public Integer visitId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    public Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_card_id", nullable = false)
    public MedicalCard medicalCard;

    @ManyToOne
    @JoinColumn(name = "cabinet_id", nullable = false)
    public Cabinet cabinet;

    @Column(name = "full_time_visit", nullable = false)
    public String timeVisit;

    @Column(name = "diagnosis", nullable = false)
    public String diagnosis;

    @Column(name = "treatment", nullable = false)
    public String treatment;

    @Override
    public String toString() {
        return "visit_id=" + visitId +
                "\t| doctor='" + doctor + '\'' +
                "\t| medical_card='" + medicalCard + "\'" +
                "\t| cabinet='" + cabinet + "\'" +
                "\t| full_time_visit='" + timeVisit + "\'" +
                "\t| diagnosis='" + diagnosis + "\'" +
                "\t| treatment='" + treatment + "\'";
    }
}
