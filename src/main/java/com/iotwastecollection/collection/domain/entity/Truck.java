package com.iotwastecollection.collection.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotwastecollection.collection.domain.enums.TruckStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trucks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "immatriculation", nullable = false, unique = true, length = 50)
    private String immatriculation;

    @Column(nullable = false, length = 100)
    private String marque;

    @Column(nullable = false, length = 100)
    private String modele;

    @Column(name = "capacite_maximale", nullable = false)
    private Integer capaciteMaximale; // en kg

    @Column(name = "date_achat", nullable = false)
    private LocalDate dateAchat;

    @Column(name = "date_dernier_entretien", nullable = false)
    private LocalDate dateDernierEntretien;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TruckStatus status = TruckStatus.AVAILABLE;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CollectionRoute> routes = new ArrayList<>();
}
