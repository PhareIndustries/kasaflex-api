package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.station.StationResponseDTO;
import com.kasaflex.api.Entities.Station;

import java.util.List;

public class StationMapper {

    public StationResponseDTO toResponse(Station station) {
        return new StationResponseDTO(
                station.getIdStation(),
                station.getNomStation(),
                station.getAdresse(),
                station.getLatitude(),
                station.getLongitude(),
                station.getDateInstallation(),
                station.getModele().getIdModele(),
                station.getClient().getIdClient()
        );
    }
    public List<StationResponseDTO> toResponseList(List<Station> stations) {
        return stations.stream()
                .map(this::toResponse)
                .toList();
    }
}
