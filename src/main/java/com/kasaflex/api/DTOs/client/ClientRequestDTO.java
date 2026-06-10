package com.kasaflex.api.DTOs.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    @NotBlank
    private String nom;

    private String prenom;

    @NotBlank
    private String adresse;

    @NotBlank
    private String telephone;

    @NotBlank
    @Email
    private String mail;
}
