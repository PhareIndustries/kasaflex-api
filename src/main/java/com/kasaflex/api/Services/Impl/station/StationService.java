package com.kasaflex.api.Services.Impl.station;

import com.kasaflex.api.DTOs.station.StationRequestDTO;
import com.kasaflex.api.DTOs.station.StationResponseDTO;
import com.kasaflex.api.Entities.Client;
import com.kasaflex.api.Entities.Modele;
import com.kasaflex.api.Entities.Station;
import com.kasaflex.api.Mappers.StationMapper;
import com.kasaflex.api.Repositories.client.ClientRepository;
import com.kasaflex.api.Repositories.modele.ModeleRepository;
import com.kasaflex.api.Repositories.station.StationRepository;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.station.IStationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService implements IStationService {
    private final StationRepository stationRepository;
    private final ModeleRepository modeleRepository;
    private final ClientRepository clientRepository;
    private final AuthorizationService authorizationService;

    @Override
    @Transactional
    public StationResponseDTO save(StationRequestDTO request) {
        authorizationService.ensureAdmin();

        Modele modele = modeleRepository.findById(request.getModele())
                .orElseThrow(() -> new EntityNotFoundException("Modèle introuvable : " + request.getModele()));

        Client client = clientRepository.findById(request.getClient())
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + request.getClient()));

        Station station = new Station();
        station.setNomStation(request.getNomStation());
        station.setAdresse(request.getAdresse());
        station.setLatitude(request.getLatitude());
        station.setLongitude(request.getLongitude());
        station.setDateInstallation(request.getDateInstallation());
        station.setModele(modele);
        station.setClient(client);
        Station saved = stationRepository.save(station);
        StationMapper map = new StationMapper();
        return map.toResponse(saved);
    }

    @Override
    public List<StationResponseDTO> findAll() {
        authorizationService.ensureAuthenticatedUtilisateur();

        StationMapper mapper = new StationMapper();

        return stationRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public StationResponseDTO update(StationRequestDTO request, String idStation) {
        authorizationService.ensureAuthenticatedUtilisateur();

        Station station = stationRepository.findById(idStation)
                .orElseThrow(() -> new EntityNotFoundException("Station introuvable : " + idStation));

        Modele modele = modeleRepository.findById(request.getModele())
                .orElseThrow(() -> new EntityNotFoundException("Modèle introuvable : " + request.getModele()));

        Client client = clientRepository.findById(request.getClient())
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + request.getClient()));

        station.setNomStation(request.getNomStation());
        station.setAdresse(request.getAdresse());
        station.setLatitude(request.getLatitude());
        station.setLongitude(request.getLongitude());
        station.setDateInstallation(request.getDateInstallation());
        station.setModele(modele);
        station.setClient(client);

        Station updated = stationRepository.save(station);

        StationMapper mapper = new StationMapper();
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public StationResponseDTO findById(String idStation) {
        authorizationService.ensureAuthenticatedUtilisateur();

        Station station = stationRepository.findById(idStation)
                .orElseThrow(() -> new EntityNotFoundException("Station introuvable : " + idStation));

        StationMapper mapper = new StationMapper();
        return mapper.toResponse(station);
    }
}
