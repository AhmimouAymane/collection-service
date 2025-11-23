package com.iotwastecollection.collection.service.impl;

import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.repository.CollectionRouteRepository;
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

    @Autowired
    public CollectionRouteServiceImpl(CollectionRouteRepository collectionRouteRepository) {
        this.collectionRouteRepository = collectionRouteRepository;
    }

    @Override
    public CollectionRoute createCollectionRoute(CollectionRoute route) {
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
        existing.setDriver(route.getDriver());
        existing.setTruck(route.getTruck());

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
