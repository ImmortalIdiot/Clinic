package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cabinets")
public class Cabinet {
    @Id
    @Column(name = "cabinet_id", updatable = false, nullable = false, insertable = false, unique = true)
    public Integer cabinetId;

    @Column(name = "floor", nullable = false)
    public Integer floor;

    @Override
    public String toString() {
        return "id=" + cabinetId + "\t| floor='" + floor + '\'';
    }
}
