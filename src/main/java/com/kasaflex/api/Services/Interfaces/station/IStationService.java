package com.kasaflex.api.Services.Interfaces.station;

import com.kasaflex.api.DTOs.station.StationRequestDTO;
import com.kasaflex.api.DTOs.station.StationResponseDTO;

import java.util.List;

public interface IStationService {
    StationResponseDTO save(StationRequestDTO dto);
    List<StationResponseDTO> findAll();
    StationResponseDTO update(StationRequestDTO item, String idStation);
    StationResponseDTO findById(String idStation);
}
