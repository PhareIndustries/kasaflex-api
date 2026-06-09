package com.kasaflex.api.DTOs.station;

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
public class StationResponseDTO {
    private String idStation;
    private String nomStation;
    private String adresse;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDate dateInstallation;
    private String idModele;
    private String idClient;
}