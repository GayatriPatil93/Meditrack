package com.backend.meditrack.dto;

import com.backend.meditrack.entity.DoseStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class DashboardDoseResponse {

    private Long doseLogId;

    private String medicationName;

    private String dosage;

    private LocalTime scheduledTime;

    private LocalDate doseDate;

    private DoseStatus status;

    public DashboardDoseResponse() {
        }

        public DashboardDoseResponse(
                Long doseLogId,
                String medicationName,
                String dosage,
                LocalTime scheduledTime,
                LocalDate doseDate,
                DoseStatus status
        ) {
            this.doseLogId = doseLogId;
            this.medicationName = medicationName;
            this.dosage = dosage;
            this.scheduledTime = scheduledTime;
            this.doseDate = doseDate;
            this.status = status;
        }

        public Long getDoseLogId() {
            return doseLogId;
        }

        public void setDoseLogId(Long doseLogId) {
            this.doseLogId = doseLogId;
        }

        public String getMedicationName() {
            return medicationName;
        }

        public void setMedicationName(
                String medicationName
        ) {
            this.medicationName = medicationName;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public LocalTime getScheduledTime() {
            return scheduledTime;
        }

        public void setScheduledTime(
                LocalTime scheduledTime
        ) {
            this.scheduledTime = scheduledTime;
        }

        public LocalDate getDoseDate() {
            return doseDate;
        }

        public void setDoseDate(
                LocalDate doseDate
        ) {
            this.doseDate = doseDate;
        }

        public DoseStatus getStatus() {
            return status;
        }

        public void setStatus(DoseStatus status) {
            this.status = status;

    }
}
