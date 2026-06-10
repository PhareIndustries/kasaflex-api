package com.kasaflex.api.DTOs.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private String idClient;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;
}
