package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_cards")
public class MedicalCard {
    @Id
    @Column(name = "card_id", nullable = false, unique = true)
    public Integer card_id;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    public Integer patientId;

    @Column(name = "has_digital_copy", nullable = false)
    public Boolean hasDigitalCopy;

    @Column
    public String[] history;
}