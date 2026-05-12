package com.backend.meditrack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dose_logs")
public class DoseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate doseDate;

    @Enumerated(EnumType.STRING)
    private DoseStatus status;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public DoseLog() {
    }

    public DoseLog(Long id,
                   LocalDate doseDate,
                   DoseStatus status,
                   Schedule schedule) {

        this.id = id;
        this.doseDate = doseDate;
        this.status = status;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDoseDate() {
        return doseDate;
    }

    public void setDoseDate(LocalDate doseDate) {
        this.doseDate = doseDate;
    }

    public DoseStatus getStatus() {
        return status;
    }

    public void setStatus(DoseStatus status) {
        this.status = status;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

