package com.iotwastecollection.collection.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotwastecollection.collection.domain.enums.ScheduleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_heure_debut", nullable = false)
    private LocalDateTime dateHeureDebut;

    @Column(name = "date_heure_fin", nullable = false)
    private LocalDateTime dateHeureFin;

    @Column(length = 500)
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status = ScheduleStatus.PLANNED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonIgnore
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", nullable = false)
    @JsonIgnore
    private Truck truck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    @JsonIgnore
    private CollectionRoute route;
}
