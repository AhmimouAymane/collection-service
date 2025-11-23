package com.iotwastecollection.collection.service.inter;

import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.enums.DriverStatus;

import java.util.List;

public interface IDriverService {
    Driver createDriver(Driver driver);
    Driver getDriverById(Long id);
    List<Driver> getAllDrivers();
    Driver updateDriver(Driver driver);
    void deleteDriver(Long id);
    Driver findByEmail(String email);
    List<Driver> findByStatus(DriverStatus status);
}
