package com.kasaflex.api.Services.Interfaces.client;

import com.kasaflex.api.DTOs.client.ClientRequestDTO;
import com.kasaflex.api.DTOs.client.ClientResponseDTO;

import java.util.List;

public interface IClientService {

    ClientResponseDTO save(ClientRequestDTO request, String userId);

    List<ClientResponseDTO> findAll();

    ClientResponseDTO findById(String idClient);

    ClientResponseDTO update(ClientRequestDTO request, String idClient, String userId, String clientIdHeader);

    void delete(String idClient, String userId);
}
