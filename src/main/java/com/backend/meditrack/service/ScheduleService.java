package com.backend.meditrack.service;

import com.backend.meditrack.dto.ScheduleRequest;
import com.backend.meditrack.entity.Medication;
import com.backend.meditrack.entity.Schedule;
import com.backend.meditrack.entity.User;
import com.backend.meditrack.exception.ResourceNotFoundException;
import com.backend.meditrack.repository.MedicationRepository;
import com.backend.meditrack.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public Schedule createSchedule(ScheduleRequest request){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Medication medication =
                medicationRepository
                        .findById(request.getMedicationID())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Medication not found"
                                )
                        );

        if (!medication.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized Access");
        }

        Schedule schedule = new Schedule();

        schedule.setScheduledTime(request.getScheduledTime());
        schedule.setDayofWeek(request.getDayofWeek());
        schedule.setMedication(medication);

        return scheduleRepository.save(schedule);
    }

}
