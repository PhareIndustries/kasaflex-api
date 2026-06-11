package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;
import com.kasaflex.api.Entities.Utilisateur;

public class UtilisateurMapper {

    public UtilisateurResponseDTO toResponse(Utilisateur utilisateur) {
        return toResponse(utilisateur, false);
    }

    public UtilisateurResponseDTO toResponse(Utilisateur utilisateur, boolean includePassword) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO(
                utilisateur.getIdUtilisateur(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getMail(),
                utilisateur.getRole().getIdRole(),
                includePassword ? utilisateur.getMotDePasse() : null
        );
        return dto;
    }
}
