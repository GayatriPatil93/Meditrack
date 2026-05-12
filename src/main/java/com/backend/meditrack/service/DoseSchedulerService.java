package com.backend.meditrack.service;

import com.backend.meditrack.entity.DoseLog;
import com.backend.meditrack.entity.DoseStatus;
import com.backend.meditrack.entity.Schedule;
import com.backend.meditrack.repository.DoseLogRepository;
import com.backend.meditrack.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoseSchedulerService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DoseLogRepository doseLogRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void generateDailyDoseLogs() {

        System.out.println("Scheduler Running...");

        List<Schedule> schedules = scheduleRepository.findAll();

        LocalDate today = LocalDate.now();

        for (Schedule schedule : schedules) {
            boolean exists =
                    doseLogRepository
                            .findByScheduleAndDoseDate(
                                    schedule,
                                    today
                            ).isPresent();

            if (!exists) {

                DoseLog doseLog = new DoseLog();

                doseLog.setDoseDate(today);

                doseLog.setStatus(DoseStatus.MISSED);

                doseLog.setSchedule(schedule);

                doseLogRepository.save(doseLog);

                System.out.println("DoseLog Created");
            }
        }
    }
}
