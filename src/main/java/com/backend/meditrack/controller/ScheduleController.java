package com.backend.meditrack.controller;

import com.backend.meditrack.dto.ScheduleRequest;
import com.backend.meditrack.entity.Schedule;
import com.backend.meditrack.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleRequest request) {
        return scheduleService.createSchedule(request);
    }
}
