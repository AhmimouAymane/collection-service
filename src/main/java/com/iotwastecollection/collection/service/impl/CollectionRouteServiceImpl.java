package com.iotwastecollection.collection.service.impl;

import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.repository.CollectionRouteRepository;
import com.iotwastecollection.collection.repository.DriverRepository;
import com.iotwastecollection.collection.repository.TruckRepository;
import com.iotwastecollection.collection.service.inter.ICollectionRouteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectionRouteServiceImpl implements ICollectionRouteService {
    private final CollectionRouteRepository collectionRouteRepository;
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;

    @Autowired
    public CollectionRouteServiceImpl(CollectionRouteRepository collectionRouteRepository,
                                      DriverRepository driverRepository,
                                      TruckRepository truckRepository) {
        this.collectionRouteRepository = collectionRouteRepository;
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public CollectionRoute createCollectionRoute(CollectionRoute route) {
        // Load Driver entity if it exists
        if (route.getDriver() != null && route.getDriver().getId() != null) {
            Driver driver = driverRepository.findById(route.getDriver().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + route.getDriver().getId()));
            route.setDriver(driver);
        }
        
        // Load Truck entity if it exists
        if (route.getTruck() != null && route.getTruck().getId() != null) {
            Truck truck = truckRepository.findById(route.getTruck().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Truck not found with id: " + route.getTruck().getId()));
            route.setTruck(truck);
        }
        
        return collectionRouteRepository.save(route);
    }

    @Override
    @Transactional(readOnly = true)
    public CollectionRoute getCollectionRouteById(Long id) {
        return collectionRouteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CollectionRoute not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionRoute> getAllCollectionRoutes() {
        return collectionRouteRepository.findAll();
    }

    @Override
    public CollectionRoute updateCollectionRoute(CollectionRoute route) {
        CollectionRoute existing = collectionRouteRepository.findById(route.getId())
                .orElseThrow(() -> new EntityNotFoundException("CollectionRoute not found: " + route.getId()));

        existing.setNom(route.getNom());
        existing.setDescription(route.getDescription());
        existing.setDateDebut(route.getDateDebut());
        existing.setDateFin(route.getDateFin());
        existing.setDistanceEstimee(route.getDistanceEstimee());
        existing.setDureeEstimee(route.getDureeEstimee());
        existing.setNombreConteneurs(route.getNombreConteneurs());
        existing.setOptimise(route.getOptimise());
        
        // Load Driver entity if it exists
        if (route.getDriver() != null && route.getDriver().getId() != null) {
            Driver driver = driverRepository.findById(route.getDriver().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + route.getDriver().getId()));
            existing.setDriver(driver);
        }
        
        // Load Truck entity if it exists
        if (route.getTruck() != null && route.getTruck().getId() != null) {
            Truck truck = truckRepository.findById(route.getTruck().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Truck not found with id: " + route.getTruck().getId()));
            existing.setTruck(truck);
        }

        return collectionRouteRepository.save(existing);
    }

    @Override
    public void deleteCollectionRoute(Long id) {
        if (collectionRouteRepository.existsById(id)) {
            collectionRouteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("CollectionRoute not found: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionRoute> findByDriverId(Long driverId) {
        return collectionRouteRepository.findByDriverId(driverId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionRoute> findByTruckId(Long truckId) {
        return collectionRouteRepository.findByTruckId(truckId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionRoute> findByOptimise(Boolean optimise) {
        return collectionRouteRepository.findByOptimise(optimise);
    }
}
