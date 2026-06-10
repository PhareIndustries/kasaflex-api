package com.kasaflex.api.DTOs.modele;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModeleRequestDTO {

    @NotBlank
    private String nomModele;

    @NotBlank
    private String numeroModele;
}
