package com.iotwastecollection.collection.service.inter;

import com.iotwastecollection.collection.domain.entity.CollectionRoute;

import java.util.List;

public interface ICollectionRouteService {
    CollectionRoute createCollectionRoute(CollectionRoute route);
    CollectionRoute getCollectionRouteById(Long id);
    List<CollectionRoute> getAllCollectionRoutes();
    CollectionRoute updateCollectionRoute(CollectionRoute route);
    void deleteCollectionRoute(Long id);
    List<CollectionRoute> findByDriverId(Long driverId);
    List<CollectionRoute> findByTruckId(Long truckId);
    List<CollectionRoute> findByOptimise(Boolean optimise);
}
