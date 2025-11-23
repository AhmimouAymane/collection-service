package com.iotwastecollection.collection.domain.dto.request;

import com.iotwastecollection.collection.domain.enums.DriverStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverRequest {
    @NotBlank(message = "Nom is required")
    private String nom;

    @NotBlank(message = "Prenom is required")
    private String prenom;

    @NotBlank(message = "Numéro de permis is required")
    private String numeroPermis;

    @NotBlank(message = "Telephone is required")
    private String telephone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Date d'embauche is required")
    private LocalDate dateEmbauche;

    private DriverStatus status = DriverStatus.AVAILABLE;
}
