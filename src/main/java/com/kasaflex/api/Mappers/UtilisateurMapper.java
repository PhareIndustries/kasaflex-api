package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;
import com.kasaflex.api.Entities.Utilisateur;

public class UtilisateurMapper {

    public UtilisateurResponseDTO toResponse(Utilisateur utilisateur) {
        return new UtilisateurResponseDTO(
                utilisateur.getIdUtilisateur(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getRole().getIdRole()
        );
    }
}
