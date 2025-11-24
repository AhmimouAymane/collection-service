package com.iotwastecollection.collection.domain.dto.response;

import com.iotwastecollection.collection.domain.enums.TruckStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TruckResponse {
    private Long id;
    private String immatriculation;
    private String marque;
    private String modele;
    private Integer capaciteMaximale;
    private LocalDate dateAchat;
    private LocalDate dateDernierEntretien;
    private TruckStatus status;
}
