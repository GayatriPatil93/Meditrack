package com.backend.meditrack.service;

import com.backend.meditrack.dto.MedicationRequest;
import com.backend.meditrack.entity.Medication;
import com.backend.meditrack.entity.User;
import com.backend.meditrack.exception.ResourceNotFoundException;
import com.backend.meditrack.repository.MedicationRepository;
import com.backend.meditrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private UserRepository userRepository;

    public Medication addMedication(MedicationRequest request) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Medication medication = new Medication();

        medication.setName(request.getName());
        medication.setDosage(request.getDosage());
        medication.setFrequency(request.getFrequency());
        medication.setStartdate(request.getStartdate());
        medication.setEnddate(request.getEnddate());

        medication.setUser(user);

        return medicationRepository.save(medication);
    }

    public List<Medication> getUserMedications() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return medicationRepository.findByUser(user);
    }

    public Medication updateMedication(Long id, MedicationRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medication medication = medicationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medication Not found."));


        if (!medication.getUser().getId()
                .equals(user.getId())) {
            throw new RuntimeException("Unauthorized Access");
        }

        medication.setName(request.getName());
        medication.setDosage(request.getDosage());
        medication.setFrequency(request.getFrequency());
        medication.setStartdate(request.getStartdate());
        medication.setEnddate(request.getEnddate());

        return medicationRepository.save(medication);
    }

    public String deleteMedication(Long id) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Medication medication = medicationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Medication not found"));

        if (!medication.getUser().getId()
                .equals(user.getId())) {
            throw new RuntimeException("Unauthorized Access");
        }

        medicationRepository.delete(medication);

        return "Medication Deleted Successfully";
    }
}
