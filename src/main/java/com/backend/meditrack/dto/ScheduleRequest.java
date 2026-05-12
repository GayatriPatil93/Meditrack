package com.backend.meditrack.dto;

import java.time.LocalTime;

public class ScheduleRequest {
    private LocalTime scheduledTime;
    private String dayofWeek;
    private Long medicationID;

    public ScheduleRequest() {
    }

    public ScheduleRequest(LocalTime scheduledTime, String dayofWeek, Long medicationID) {
        this.scheduledTime = scheduledTime;
        this.dayofWeek = dayofWeek;
        this.medicationID = medicationID;
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

    public Long getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(Long medicationID) {
        this.medicationID = medicationID;
    }
}
