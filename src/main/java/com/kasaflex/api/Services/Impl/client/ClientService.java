package com.kasaflex.api.Services.Impl.client;

import com.kasaflex.api.DTOs.client.ClientRequestDTO;
import com.kasaflex.api.DTOs.client.ClientResponseDTO;
import com.kasaflex.api.Entities.Client;
import com.kasaflex.api.Mappers.ClientMapper;
import com.kasaflex.api.Repositories.client.ClientRepository;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.client.IClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final AuthorizationService authorizationService;

    @Override
    @Transactional
    public ClientResponseDTO save(ClientRequestDTO request) {
        authorizationService.ensureAdmin();

        Client client = new Client();
        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setAdresse(request.getAdresse());
        client.setTelephone(request.getTelephone());
        client.setMail(request.getMail());

        Client saved = clientRepository.save(client);
        return new ClientMapper().toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDTO> findAll() {
        authorizationService.ensureAdmin();

        ClientMapper mapper = new ClientMapper();
        return clientRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDTO findById(String idClient) {
        authorizationService.ensureAdmin();

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + idClient));

        return new ClientMapper().toResponse(client);
    }

    @Override
    @Transactional
    public ClientResponseDTO update(ClientRequestDTO request, String idClient) {
        authorizationService.ensureCanUpdateClient(idClient);

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + idClient));

        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setAdresse(request.getAdresse());
        client.setTelephone(request.getTelephone());
        client.setMail(request.getMail());

        Client updated = clientRepository.save(client);
        return new ClientMapper().toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String idClient) {
        authorizationService.ensureAdmin();

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + idClient));

        clientRepository.delete(client);
    }
}
