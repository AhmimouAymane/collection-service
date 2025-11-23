package com.iotwastecollection.collection.service.inter;

import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;

import java.util.List;

public interface IScheduleService {
    Schedule createSchedule(Schedule schedule);
    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    Schedule updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
    List<Schedule> findByStatus(ScheduleStatus status);
    List<Schedule> findByDriverId(Long driverId);
    List<Schedule> findByTruckId(Long truckId);
}
