package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.user.UserResponseDTO;
import com.kasaflex.api.Entities.User;

public class UserMapper {

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getIdUser(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getRole().getIdRole()
        );
    }
}
