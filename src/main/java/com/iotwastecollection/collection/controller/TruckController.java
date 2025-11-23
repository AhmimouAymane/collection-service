package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.domain.enums.TruckStatus;
import com.iotwastecollection.collection.service.inter.ITruckService;
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

    @PostMapping
    public ResponseEntity<Truck> createTruck(@RequestBody Truck truck) {
        Truck createdTruck = truckService.createTruck(truck);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTruck);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> getTruckById(@PathVariable Long id) {
        Truck truck = truckService.getTruckById(id);
        return ResponseEntity.ok(truck);
    }

    @GetMapping
    public ResponseEntity<List<Truck>> getAllTrucks() {
        List<Truck> trucks = truckService.getAllTrucks();
        return ResponseEntity.ok(trucks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Truck> updateTruck(@PathVariable Long id, @RequestBody Truck truck) {
        truck.setId(id);
        Truck updatedTruck = truckService.updateTruck(truck);
        return ResponseEntity.ok(updatedTruck);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTruck(@PathVariable Long id) {
        truckService.deleteTruck(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/immatriculation/{immatriculation}")
    public ResponseEntity<Truck> getTruckByImmatriculation(@PathVariable String immatriculation) {
        Truck truck = truckService.findByImmatriculation(immatriculation);
        return ResponseEntity.ok(truck);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Truck>> getTrucksByStatus(@PathVariable TruckStatus status) {
        List<Truck> trucks = truckService.findByStatus(status);
        return ResponseEntity.ok(trucks);
    }
}
