package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.dto.request.ScheduleRequest;
import com.iotwastecollection.collection.domain.dto.response.ScheduleResponse;
import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import com.iotwastecollection.collection.mapper.ScheduleMapper;
import com.iotwastecollection.collection.service.inter.IScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody ScheduleRequest request) {
        Schedule schedule = scheduleMapper.toEntity(request);
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        ScheduleResponse response = scheduleMapper.toResponse(createdSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        ScheduleResponse response = scheduleMapper.toResponse(schedule);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleResponse> responses = scheduleMapper.toResponseList(schedules);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequest request) {
        Schedule schedule = scheduleMapper.toEntity(request);
        schedule.setId(id);
        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
        ScheduleResponse response = scheduleMapper.toResponse(updatedSchedule);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByStatus(@PathVariable ScheduleStatus status) {
        List<Schedule> schedules = scheduleService.findByStatus(status);
        List<ScheduleResponse> responses = scheduleMapper.toResponseList(schedules);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByDriver(@PathVariable Long driverId) {
        List<Schedule> schedules = scheduleService.findByDriverId(driverId);
        List<ScheduleResponse> responses = scheduleMapper.toResponseList(schedules);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/truck/{truckId}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByTruck(@PathVariable Long truckId) {
        List<Schedule> schedules = scheduleService.findByTruckId(truckId);
        List<ScheduleResponse> responses = scheduleMapper.toResponseList(schedules);
        return ResponseEntity.ok(responses);
    }
}
