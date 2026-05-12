package com.backend.meditrack.controller;

import com.backend.meditrack.dto.MedicationRequest;
import com.backend.meditrack.entity.Medication;
import com.backend.meditrack.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping
    public Medication addMedication(@RequestBody MedicationRequest request){
        return medicationService.addMedication(request);
    }

    @GetMapping
    public List<Medication> getUSerMedications(){
        return medicationService.getUserMedications();
    }
    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody MedicationRequest request) {
        return medicationService.updateMedication(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteMedication(@PathVariable Long id) {
        return medicationService.deleteMedication(id);
    }

}
