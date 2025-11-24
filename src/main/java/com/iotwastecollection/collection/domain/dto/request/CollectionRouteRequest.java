package com.iotwastecollection.collection.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionRouteRequest {
    @NotBlank(message = "Nom is required")
    private String nom;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Date début is required")
    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    @NotNull(message = "Distance estimée is required")
    @Positive(message = "Distance estimée must be positive")
    private Double distanceEstimee;

    @NotNull(message = "Durée estimée is required")
    @Positive(message = "Durée estimée must be positive")
    private Integer dureeEstimee;

    private Integer nombreConteneurs;

    private Boolean optimise = false;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @NotNull(message = "Truck ID is required")
    private Long truckId;
}
