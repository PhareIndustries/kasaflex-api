package com.kasaflex.api.Services.Interfaces.statut;

import com.kasaflex.api.DTOs.statut.StatutRequestDTO;
import com.kasaflex.api.DTOs.statut.StatutResponseDTO;

import java.util.List;

public interface IStatutService {
    StatutResponseDTO save(StatutRequestDTO dto);
    List<StatutResponseDTO> findAll();
    StatutResponseDTO update(StatutRequestDTO item, String idStatut);
    StatutResponseDTO findById(String idStatut);
    void delete(String idStatut);
}
