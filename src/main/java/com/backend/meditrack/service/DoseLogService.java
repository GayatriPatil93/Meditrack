package com.backend.meditrack.service;

import com.backend.meditrack.dto.ComplianceResponse;
import com.backend.meditrack.dto.DashboardDoseResponse;
import com.backend.meditrack.entity.DoseLog;
import com.backend.meditrack.entity.DoseStatus;
import com.backend.meditrack.entity.User;
import com.backend.meditrack.exception.ResourceNotFoundException;
import com.backend.meditrack.repository.DoseLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoseLogService {

    @Autowired
    private DoseLogRepository doseLogRepository;


        public List<DashboardDoseResponse> getTodayDoseLogs() {
            User user = (User)
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal();

            List<DoseLog> logs =
                    doseLogRepository
                            .findTodayDoseLogsByUser(
                                    user.getId(),
                                    LocalDate.now()
                            );

            return logs.stream()
                    .map(log -> new DashboardDoseResponse(

                            log.getId(),

                            log.getSchedule()
                                    .getMedication()
                                    .getName(),

                            log.getSchedule()
                                    .getMedication()
                                    .getDosage(),

                            log.getSchedule()
                                    .getScheduledTime(),

                            log.getDoseDate(),

                            log.getStatus()

                    ))
                    .toList();
        }

        public DoseLog markDoseStatus(
                Long id,
                DoseStatus status
        ) {

            User user = (User)
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal();

            DoseLog doseLog = doseLogRepository.findById(id)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("DoseLog not found"));

            Long ownerId = doseLog.getSchedule()
                            .getMedication()
                            .getUser()
                            .getId();

            if (!ownerId.equals(user.getId())) {

                throw new RuntimeException("Unauthorized Access");
            }

            doseLog.setStatus(status);

            return doseLogRepository.save(doseLog);
        }

    public ComplianceResponse getWeeklyCompliance() {

        User user = (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        LocalDate today = LocalDate.now();

        LocalDate sevenDaysAgo =
                today.minusDays(6);

        List<DoseLog> logs = doseLogRepository
                        .findDoseLogsBetweenDates(
                                user.getId(),
                                sevenDaysAgo,
                                today);

        long takenCount = logs.stream()
                .filter(log -> log.getStatus() == DoseStatus.TAKEN).count();

        long skippedCount = logs.stream()
                .filter(log -> log.getStatus() == DoseStatus.SKIPPED).count();

        long missedCount = logs.stream()
                .filter(log -> log.getStatus() == DoseStatus.MISSED).count();

        long total = takenCount + skippedCount + missedCount;

        double compliance = 0;

        if (total > 0) {
            compliance = ((double) takenCount / total) * 100;
        }

        return new ComplianceResponse(
                takenCount,
                skippedCount,
                missedCount,
                compliance
        );
    }

}
