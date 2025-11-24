package com.iotwastecollection.collection.service.impl;

import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import com.iotwastecollection.collection.repository.CollectionRouteRepository;
import com.iotwastecollection.collection.repository.DriverRepository;
import com.iotwastecollection.collection.repository.ScheduleRepository;
import com.iotwastecollection.collection.repository.TruckRepository;
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
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final CollectionRouteRepository collectionRouteRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               DriverRepository driverRepository,
                               TruckRepository truckRepository,
                               CollectionRouteRepository collectionRouteRepository) {
        this.scheduleRepository = scheduleRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.collectionRouteRepository = collectionRouteRepository;
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        // Load Driver entity if it exists
        if (schedule.getDriver() != null && schedule.getDriver().getId() != null) {
            Driver driver = driverRepository.findById(schedule.getDriver().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + schedule.getDriver().getId()));
            schedule.setDriver(driver);
        }
        
        // Load Truck entity if it exists
        if (schedule.getTruck() != null && schedule.getTruck().getId() != null) {
            Truck truck = truckRepository.findById(schedule.getTruck().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Truck not found with id: " + schedule.getTruck().getId()));
            schedule.setTruck(truck);
        }
        
        // Load CollectionRoute entity if it exists (optional)
        if (schedule.getRoute() != null && schedule.getRoute().getId() != null) {
            CollectionRoute route = collectionRouteRepository.findById(schedule.getRoute().getId())
                    .orElseThrow(() -> new EntityNotFoundException("CollectionRoute not found with id: " + schedule.getRoute().getId()));
            schedule.setRoute(route);
        }
        
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
        
        // Load Driver entity if it exists
        if (schedule.getDriver() != null && schedule.getDriver().getId() != null) {
            Driver driver = driverRepository.findById(schedule.getDriver().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + schedule.getDriver().getId()));
            existing.setDriver(driver);
        }
        
        // Load Truck entity if it exists
        if (schedule.getTruck() != null && schedule.getTruck().getId() != null) {
            Truck truck = truckRepository.findById(schedule.getTruck().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Truck not found with id: " + schedule.getTruck().getId()));
            existing.setTruck(truck);
        }
        
        // Load CollectionRoute entity if it exists (optional)
        if (schedule.getRoute() != null && schedule.getRoute().getId() != null) {
            CollectionRoute route = collectionRouteRepository.findById(schedule.getRoute().getId())
                    .orElseThrow(() -> new EntityNotFoundException("CollectionRoute not found with id: " + schedule.getRoute().getId()));
            existing.setRoute(route);
        } else {
            existing.setRoute(null); // Allow clearing the route
        }

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
