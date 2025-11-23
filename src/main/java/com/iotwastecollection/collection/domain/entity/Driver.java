package com.iotwastecollection.collection.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotwastecollection.collection.domain.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(name = "numero_permis", nullable = false, unique = true, length = 50)
    private String numeroPermis;

    @Column(nullable = false, length = 20)
    private String telephone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "date_embauche", nullable = false)
    private LocalDate dateEmbauche;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status = DriverStatus.AVAILABLE;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CollectionRoute> routes = new ArrayList<>();
}
