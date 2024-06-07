package com.immortalidiot.clinicdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name = "schedule_id", nullable = false, unique = true)
    public Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    public Doctor doctor;

    @Column(name = "day_of_week", nullable = false)
    public String dayOfWeek;

    @Column(name = "time", nullable = false)
    public String time;

    @Override
    public String toString() {
        return "id=" + scheduleId +
                "\t| doctorId='" + doctor + '\'' +
                "\t| dayOfWeek='" + dayOfWeek + "\'" +
                "\t| time='" + time + "\'";
    }
}
