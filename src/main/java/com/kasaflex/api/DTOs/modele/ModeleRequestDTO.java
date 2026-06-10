package com.kasaflex.api.DTOs.modele;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @NotBlank(message = "Le nom du modèle est obligatoire")
    @JsonProperty("nommodele")
    @JsonAlias({"nomModele", "nom_modele"})
    private String nomModele;

    @NotBlank(message = "Le numéro du modèle est obligatoire")
    @JsonProperty("numeromodele")
    @JsonAlias({"numeroModele", "numero_modele"})
    private String numeroModele;
}
