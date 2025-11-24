package com.iotwastecollection.collection.domain.dto.request;

import com.iotwastecollection.collection.domain.enums.TruckStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TruckRequest {
    @NotBlank(message = "Immatriculation is required")
    private String immatriculation;

    @NotBlank(message = "Marque is required")
    private String marque;

    @NotBlank(message = "Modele is required")
    private String modele;

    @NotNull(message = "Capacité maximale is required")
    @Positive(message = "Capacité maximale must be positive")
    private Integer capaciteMaximale;

    @NotNull(message = "Date d'achat is required")
    private LocalDate dateAchat;

    @NotNull(message = "Date dernier entretien is required")
    private LocalDate dateDernierEntretien;

    private TruckStatus status = TruckStatus.AVAILABLE;
}
