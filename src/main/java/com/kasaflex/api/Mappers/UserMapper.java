package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.user.UserResponseDTO;
import com.kasaflex.api.Entities.Utilisateur;

public class UserMapper {

    public UserResponseDTO toResponse(Utilisateur user) {
        return new UserResponseDTO(
                user.getIdUtilisateur(),
                user.getNom(),
                user.getPrenom(),
                user.getMail(),
                user.getRole().getIdRole()
        );
    }
}
