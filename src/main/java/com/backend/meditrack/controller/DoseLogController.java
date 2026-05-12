package com.backend.meditrack.controller;

import com.backend.meditrack.dto.ComplianceResponse;
import com.backend.meditrack.dto.DashboardDoseResponse;
import com.backend.meditrack.entity.DoseLog;
import com.backend.meditrack.entity.DoseStatus;
import com.backend.meditrack.service.DoseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dose-logs")
public class DoseLogController {

        @Autowired
        private DoseLogService doseLogService;


        @GetMapping("/today")
        public List<DashboardDoseResponse> getTodayDoseLogs() {
            return doseLogService.getTodayDoseLogs();
        }


        @PutMapping("/{id}/taken")
        public DoseLog markTaken(@PathVariable Long id) {

            return doseLogService.markDoseStatus(id, DoseStatus.TAKEN);
        }

        @PutMapping("/{id}/skipped")
        public DoseLog markSkipped(@PathVariable Long id) {

            return doseLogService.markDoseStatus(id, DoseStatus.SKIPPED);

    }

    @GetMapping("/compliance/weekly")
    public ComplianceResponse getWeeklyCompliance() {

        return doseLogService.getWeeklyCompliance();
    }
}
