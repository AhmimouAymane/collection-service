package com.iotwastecollection.collection.service.impl;

import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import com.iotwastecollection.collection.repository.ScheduleRepository;
import com.iotwastecollection.collection.service.inter.IScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements IScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    @Transactional(readOnly = true)
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        Schedule existing = scheduleRepository.findById(schedule.getId())
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found: " + schedule.getId()));

        existing.setDateHeureDebut(schedule.getDateHeureDebut());
        existing.setDateHeureFin(schedule.getDateHeureFin());
        existing.setNotes(schedule.getNotes());
        existing.setStatus(schedule.getStatus());
        existing.setDriver(schedule.getDriver());
        existing.setTruck(schedule.getTruck());
        existing.setRoute(schedule.getRoute());

        return scheduleRepository.save(existing);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Schedule not found: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> findByStatus(ScheduleStatus status) {
        return scheduleRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> findByDriverId(Long driverId) {
        return scheduleRepository.findByDriverId(driverId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Schedule> findByTruckId(Long truckId) {
        return scheduleRepository.findByTruckId(truckId);
    }
}
