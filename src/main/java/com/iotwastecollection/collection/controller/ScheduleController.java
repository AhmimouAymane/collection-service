package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import com.iotwastecollection.collection.service.inter.IScheduleService;
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

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        schedule.setId(id);
        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Schedule>> getSchedulesByStatus(@PathVariable ScheduleStatus status) {
        List<Schedule> schedules = scheduleService.findByStatus(status);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Schedule>> getSchedulesByDriver(@PathVariable Long driverId) {
        List<Schedule> schedules = scheduleService.findByDriverId(driverId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/truck/{truckId}")
    public ResponseEntity<List<Schedule>> getSchedulesByTruck(@PathVariable Long truckId) {
        List<Schedule> schedules = scheduleService.findByTruckId(truckId);
        return ResponseEntity.ok(schedules);
    }
}
