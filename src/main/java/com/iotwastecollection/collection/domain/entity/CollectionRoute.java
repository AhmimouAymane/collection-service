package com.iotwastecollection.collection.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collection_routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 200)
    private String nom;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @Column(name = "distance_estimee", nullable = false)
    private Double distanceEstimee; // en km

    @Column(name = "duree_estimee", nullable = false)
    private Integer dureeEstimee; // en minutes

    @Column(name = "nombre_conteneurs")
    private Integer nombreConteneurs;

    @Column(nullable = false)
    private Boolean optimise = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonIgnore
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", nullable = false)
    @JsonIgnore
    private Truck truck;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();
}
