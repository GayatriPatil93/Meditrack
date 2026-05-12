package com.backend.meditrack.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime scheduledTime;
    private String dayofWeek;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    public Schedule() {

    }

    public Schedule(Long id, LocalTime scheduledTime, String dayofWeek, Medication medication) {
        this.id = id;
        this.scheduledTime = scheduledTime;
        this.dayofWeek = dayofWeek;
        this.medication = medication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(String dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
}
