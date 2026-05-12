package com.backend.meditrack.repository;

import com.backend.meditrack.entity.DoseLog;
import com.backend.meditrack.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoseLogRepository extends JpaRepository<DoseLog, Long>
{
    Optional<DoseLog> findByScheduleAndDoseDate(
            Schedule schedule,
            LocalDate doseDate
    );

    @Query("""
           SELECT d
           FROM DoseLog d
           WHERE d.schedule.medication.user.id = :userId
           AND d.doseDate = :date
           """)
    List<DoseLog> findTodayDoseLogsByUser(
            @Param("userId") Long userId,
            @Param("date") LocalDate date
    );

    @Query("""
       SELECT d
       FROM DoseLog d
       WHERE d.schedule.medication.user.id = :userId
       AND d.doseDate BETWEEN :startDate AND :endDate
       """)
    List<DoseLog> findDoseLogsBetweenDates(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}