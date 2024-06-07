package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_cards")
public class MedicalCard {
    @Id
    @Column(name = "card_id", nullable = false, unique = true)
    public Integer cardId;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    public Integer patientId;

    @Column(name = "has_digital_copy", nullable = false)
    public Boolean hasDigitalCopy;

    @Column
    public String[] history;

    @Override
    public String toString() {
        return "card_id=" + cardId +
                "\t| patient_id='" + patientId + '\'' +
                "\t| hasDigitalCopy='" + hasDigitalCopy + "\'" +
                "\t| history='" + history + "\'";
    }
}
