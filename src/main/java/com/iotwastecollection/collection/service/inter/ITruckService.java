package com.iotwastecollection.collection.service.inter;

import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.domain.enums.TruckStatus;

import java.util.List;

public interface ITruckService {
    Truck createTruck(Truck truck);
    Truck getTruckById(Long id);
    List<Truck> getAllTrucks();
    Truck updateTruck(Truck truck);
    void deleteTruck(Long id);
    Truck findByImmatriculation(String immatriculation);
    List<Truck> findByStatus(TruckStatus status);
}
