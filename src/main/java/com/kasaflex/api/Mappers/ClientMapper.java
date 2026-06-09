package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.client.ClientResponseDTO;
import com.kasaflex.api.Entities.Client;

public class ClientMapper {

    public ClientResponseDTO toResponse(Client client) {
        return new ClientResponseDTO(
                client.getIdClient(),
                client.getNom(),
                client.getPrenom(),
                client.getAdresse(),
                client.getTelephone(),
                client.getEmail()
        );
    }
}
