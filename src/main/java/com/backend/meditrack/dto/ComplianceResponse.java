package com.backend.meditrack.dto;

public class ComplianceResponse {
    private long takenCount;

    private long skippedCount;

    private long missedCount;

    private double compliancePercentage;

    public ComplianceResponse() {
    }

    public ComplianceResponse(
            long takenCount,
            long skippedCount,
            long missedCount,
            double compliancePercentage
    ) {

        this.takenCount = takenCount;
        this.skippedCount = skippedCount;
        this.missedCount = missedCount;
        this.compliancePercentage =
                compliancePercentage;
    }

    public long getTakenCount() {
        return takenCount;
    }

    public void setTakenCount(
            long takenCount
    ) {
        this.takenCount = takenCount;
    }

    public long getSkippedCount() {
        return skippedCount;
    }

    public void setSkippedCount(
            long skippedCount
    ) {
        this.skippedCount = skippedCount;
    }

    public long getMissedCount() {
        return missedCount;
    }

    public void setMissedCount(
            long missedCount
    ) {
        this.missedCount = missedCount;
    }

    public double getCompliancePercentage() {
        return compliancePercentage;
    }

    public void setCompliancePercentage(
            double compliancePercentage
    ) {
        this.compliancePercentage =
                compliancePercentage;
    }
}
