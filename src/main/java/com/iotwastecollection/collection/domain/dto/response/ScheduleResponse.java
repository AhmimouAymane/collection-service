package com.iotwastecollection.collection.domain.dto.response;

import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleResponse {
    private Long id;
    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;
    private String notes;
    private ScheduleStatus status;
    private Long driverId;
    private Long truckId;
    private Long routeId;
}
