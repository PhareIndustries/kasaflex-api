package com.kasaflex.api.DTOs.utilisateur;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String prenom;

    @NotBlank(message = "Le mail est obligatoire")
    @Email(message = "Le mail n'est pas valide")
    private String mail;

    @NotBlank(groups = OnCreate.class, message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    @Pattern(
            regexp = ".*[A-Z].*",
            message = "Le mot de passe doit contenir au moins une lettre majuscule"
    )
    private String motDePasse;

    @NotBlank(groups = OnCreate.class, message = "Le rôle est obligatoire")
    @JsonProperty("idrole")
    private String idRole;
}
