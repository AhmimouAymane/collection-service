package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.dto.request.DriverRequest;
import com.iotwastecollection.collection.domain.dto.response.DriverResponse;
import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.enums.DriverStatus;
import com.iotwastecollection.collection.mapper.DriverMapper;
import com.iotwastecollection.collection.service.inter.IDriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private IDriverService driverService;

    @Autowired
    private DriverMapper driverMapper;

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest request) {
        Driver driver = driverMapper.toEntity(request);
        Driver createdDriver = driverService.createDriver(driver);
        DriverResponse response = driverMapper.toResponse(createdDriver);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long id) {
        Driver driver = driverService.getDriverById(id);
        DriverResponse response = driverMapper.toResponse(driver);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        List<DriverResponse> responses = driverMapper.toResponseList(drivers);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(@PathVariable Long id, @Valid @RequestBody DriverRequest request) {
        Driver driver = driverMapper.toEntity(request);
        driver.setId(id);
        Driver updatedDriver = driverService.updateDriver(driver);
        DriverResponse response = driverMapper.toResponse(updatedDriver);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DriverResponse> getDriverByEmail(@PathVariable String email) {
        Driver driver = driverService.findByEmail(email);
        DriverResponse response = driverMapper.toResponse(driver);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<DriverResponse>> getDriversByStatus(@PathVariable DriverStatus status) {
        List<Driver> drivers = driverService.findByStatus(status);
        List<DriverResponse> responses = driverMapper.toResponseList(drivers);
        return ResponseEntity.ok(responses);
    }
}
