package com.backend.meditrack.repository;

import com.backend.meditrack.entity.DoseLog;
import com.backend.meditrack.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DoseLogRepository extends JpaRepository<DoseLog, Long>
{
    Optional<DoseLog> findByScheduleAndDoseDate(
            Schedule schedule,
            LocalDate doseDate
    );
}
