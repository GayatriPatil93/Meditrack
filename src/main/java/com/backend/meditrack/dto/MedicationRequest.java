package com.backend.meditrack.dto;

import java.time.LocalDate;

public class MedicationRequest {

    private String name;
    private String dosage;
    private String frequency;
    private LocalDate startdate;
    private LocalDate enddate;

    public MedicationRequest() {

    }

    public MedicationRequest(String name, String dosage, String frequency, LocalDate startdate, LocalDate enddate) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }
}
