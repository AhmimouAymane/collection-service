package com.iotwastecollection.collection.repository;

import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CollectionRouteRepository extends JpaRepository<CollectionRoute, Long> {
    List<CollectionRoute> findByDriverId(Long driverId);
    List<CollectionRoute> findByTruckId(Long truckId);
    List<CollectionRoute> findByOptimise(Boolean optimise);
    List<CollectionRoute> findByDateDebutBetween(LocalDateTime start, LocalDateTime end);
}
