package com.kasaflex.api.Services.Interfaces.user;

import com.kasaflex.api.DTOs.user.UserRequestDTO;
import com.kasaflex.api.DTOs.user.UserResponseDTO;

import java.util.List;

public interface IUserService {

    UserResponseDTO save(UserRequestDTO request);

    List<UserResponseDTO> findAll();

    UserResponseDTO findById(String idUser);

    UserResponseDTO update(UserRequestDTO request, String idUser);

    void delete(String idUser);
}
