package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_cards")
public class MedicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false, unique = true)
    public Integer cardId;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    public Patient patient;

    @Column(name = "has_digital_copy", nullable = false)
    public Boolean hasDigitalCopy;

    @Column(name = "history")
    public String[] history;

    @Override
    public String toString() {
        return "card_id=" + cardId +
                "\t| patient='" + patient + '\'' +
                "\t| hasDigitalCopy='" + hasDigitalCopy + "\'" +
                "\t| history='" + history + "\'";
    }
}
