package com.iotwastecollection.collection.domain.dto.request;

import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleRequest {
    @NotNull(message = "Date heure début is required")
    private LocalDateTime dateHeureDebut;

    @NotNull(message = "Date heure fin is required")
    private LocalDateTime dateHeureFin;

    private String notes;

    private ScheduleStatus status = ScheduleStatus.PLANNED;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @NotNull(message = "Truck ID is required")
    private Long truckId;

    private Long routeId; // Optional
}
