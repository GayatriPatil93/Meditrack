package com.backend.meditrack.repository;

import com.backend.meditrack.entity.Medication;
import com.backend.meditrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByUser(User user);
}
