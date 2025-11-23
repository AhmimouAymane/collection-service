package com.iotwastecollection.collection.domain.dto.response;

import com.iotwastecollection.collection.domain.enums.DriverStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroPermis;
    private String telephone;
    private String email;
    private LocalDate dateEmbauche;
    private DriverStatus status;
}
