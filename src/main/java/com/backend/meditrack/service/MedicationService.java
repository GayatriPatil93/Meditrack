package com.backend.meditrack.service;

import com.backend.meditrack.dto.MedicationRequest;
import com.backend.meditrack.entity.Medication;
import com.backend.meditrack.entity.User;
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
    private MedicationRepository  medicationRepository;

    @Autowired
    private UserRepository userRepository;

    public Medication addMedication(MedicationRequest request){
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
}
