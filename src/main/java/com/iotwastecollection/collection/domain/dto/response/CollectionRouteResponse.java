package com.iotwastecollection.collection.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionRouteResponse {
    private Long id;
    private String nom;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Double distanceEstimee;
    private Integer dureeEstimee;
    private Integer nombreConteneurs;
    private Boolean optimise;
    private Long driverId;
    private Long truckId;
}
