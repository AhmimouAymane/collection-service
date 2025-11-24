package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.dto.request.TruckRequest;
import com.iotwastecollection.collection.domain.dto.response.TruckResponse;
import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.domain.enums.TruckStatus;
import com.iotwastecollection.collection.mapper.TruckMapper;
import com.iotwastecollection.collection.service.inter.ITruckService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    @Autowired
    private ITruckService truckService;

    @Autowired
    private TruckMapper truckMapper;

    @PostMapping
    public ResponseEntity<TruckResponse> createTruck(@Valid @RequestBody TruckRequest request) {
        Truck truck = truckMapper.toEntity(request);
        Truck createdTruck = truckService.createTruck(truck);
        TruckResponse response = truckMapper.toResponse(createdTruck);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TruckResponse> getTruckById(@PathVariable Long id) {
        Truck truck = truckService.getTruckById(id);
        TruckResponse response = truckMapper.toResponse(truck);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TruckResponse>> getAllTrucks() {
        List<Truck> trucks = truckService.getAllTrucks();
        List<TruckResponse> responses = truckMapper.toResponseList(trucks);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TruckResponse> updateTruck(@PathVariable Long id, @Valid @RequestBody TruckRequest request) {
        Truck truck = truckMapper.toEntity(request);
        truck.setId(id);
        Truck updatedTruck = truckService.updateTruck(truck);
        TruckResponse response = truckMapper.toResponse(updatedTruck);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTruck(@PathVariable Long id) {
        truckService.deleteTruck(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/immatriculation/{immatriculation}")
    public ResponseEntity<TruckResponse> getTruckByImmatriculation(@PathVariable String immatriculation) {
        Truck truck = truckService.findByImmatriculation(immatriculation);
        TruckResponse response = truckMapper.toResponse(truck);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TruckResponse>> getTrucksByStatus(@PathVariable TruckStatus status) {
        List<Truck> trucks = truckService.findByStatus(status);
        List<TruckResponse> responses = truckMapper.toResponseList(trucks);
        return ResponseEntity.ok(responses);
    }
}
