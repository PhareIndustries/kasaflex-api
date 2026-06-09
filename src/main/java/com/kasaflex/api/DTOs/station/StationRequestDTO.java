package com.kasaflex.api.DTOs.station;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationRequestDTO {
    @NotBlank
    private String nomStation;
    @NotBlank
    private String adresse;
    @NotNull
    private BigDecimal longitude;
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private LocalDate dateInstallation;
    @NotBlank
    private String modele;
    @NotBlank
    private String client;
}
