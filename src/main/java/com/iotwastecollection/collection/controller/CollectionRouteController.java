package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.service.inter.ICollectionRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class CollectionRouteController {

    @Autowired
    private ICollectionRouteService routeService;

    @PostMapping
    public ResponseEntity<CollectionRoute> createRoute(@RequestBody CollectionRoute route) {
        CollectionRoute createdRoute = routeService.createCollectionRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionRoute> getRouteById(@PathVariable Long id) {
        CollectionRoute route = routeService.getCollectionRouteById(id);
        return ResponseEntity.ok(route);
    }

    @GetMapping
    public ResponseEntity<List<CollectionRoute>> getAllRoutes() {
        List<CollectionRoute> routes = routeService.getAllCollectionRoutes();
        return ResponseEntity.ok(routes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionRoute> updateRoute(@PathVariable Long id, @RequestBody CollectionRoute route) {
        route.setId(id);
        CollectionRoute updatedRoute = routeService.updateCollectionRoute(route);
        return ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteCollectionRoute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<CollectionRoute>> getRoutesByDriver(@PathVariable Long driverId) {
        List<CollectionRoute> routes = routeService.findByDriverId(driverId);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/truck/{truckId}")
    public ResponseEntity<List<CollectionRoute>> getRoutesByTruck(@PathVariable Long truckId) {
        List<CollectionRoute> routes = routeService.findByTruckId(truckId);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/optimise/{optimise}")
    public ResponseEntity<List<CollectionRoute>> getRoutesByOptimise(@PathVariable Boolean optimise) {
        List<CollectionRoute> routes = routeService.findByOptimise(optimise);
        return ResponseEntity.ok(routes);
    }
}
