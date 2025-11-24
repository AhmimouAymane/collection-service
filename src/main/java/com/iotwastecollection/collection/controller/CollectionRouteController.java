package com.iotwastecollection.collection.controller;

import com.iotwastecollection.collection.domain.dto.request.CollectionRouteRequest;
import com.iotwastecollection.collection.domain.dto.response.CollectionRouteResponse;
import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.mapper.CollectionRouteMapper;
import com.iotwastecollection.collection.service.inter.ICollectionRouteService;
import jakarta.validation.Valid;
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

    @Autowired
    private CollectionRouteMapper routeMapper;

    @PostMapping
    public ResponseEntity<CollectionRouteResponse> createRoute(@Valid @RequestBody CollectionRouteRequest request) {
        CollectionRoute route = routeMapper.toEntity(request);
        CollectionRoute createdRoute = routeService.createCollectionRoute(route);
        CollectionRouteResponse response = routeMapper.toResponse(createdRoute);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionRouteResponse> getRouteById(@PathVariable Long id) {
        CollectionRoute route = routeService.getCollectionRouteById(id);
        CollectionRouteResponse response = routeMapper.toResponse(route);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CollectionRouteResponse>> getAllRoutes() {
        List<CollectionRoute> routes = routeService.getAllCollectionRoutes();
        List<CollectionRouteResponse> responses = routeMapper.toResponseList(routes);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionRouteResponse> updateRoute(@PathVariable Long id, @Valid @RequestBody CollectionRouteRequest request) {
        CollectionRoute route = routeMapper.toEntity(request);
        route.setId(id);
        CollectionRoute updatedRoute = routeService.updateCollectionRoute(route);
        CollectionRouteResponse response = routeMapper.toResponse(updatedRoute);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteCollectionRoute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<CollectionRouteResponse>> getRoutesByDriver(@PathVariable Long driverId) {
        List<CollectionRoute> routes = routeService.findByDriverId(driverId);
        List<CollectionRouteResponse> responses = routeMapper.toResponseList(routes);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/truck/{truckId}")
    public ResponseEntity<List<CollectionRouteResponse>> getRoutesByTruck(@PathVariable Long truckId) {
        List<CollectionRoute> routes = routeService.findByTruckId(truckId);
        List<CollectionRouteResponse> responses = routeMapper.toResponseList(routes);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/optimise/{optimise}")
    public ResponseEntity<List<CollectionRouteResponse>> getRoutesByOptimise(@PathVariable Boolean optimise) {
        List<CollectionRoute> routes = routeService.findByOptimise(optimise);
        List<CollectionRouteResponse> responses = routeMapper.toResponseList(routes);
        return ResponseEntity.ok(responses);
    }
}
