package com.kasaflex.api.DTOs.utilisateur;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurResponseDTO {

    private String idUtilisateur;
    private String nom;
    private String prenom;
    private String mail;
    private String idRole;
}
