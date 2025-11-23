package com.iotwastecollection.collection.repository;

import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStatus(ScheduleStatus status);
    List<Schedule> findByDriverId(Long driverId);
    List<Schedule> findByTruckId(Long truckId);
    List<Schedule> findByRouteId(Long routeId);
    List<Schedule> findByDateHeureDebutBetween(LocalDateTime start, LocalDateTime end);
}
