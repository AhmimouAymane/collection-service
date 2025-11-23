package com.iotwastecollection.collection.repository;

import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.domain.enums.TruckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    Optional<Truck> findByImmatriculation(String immatriculation);
    List<Truck> findByStatus(TruckStatus status);
    boolean existsByImmatriculation(String immatriculation);
}
