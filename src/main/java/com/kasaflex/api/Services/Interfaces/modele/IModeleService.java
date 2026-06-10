package com.kasaflex.api.Services.Interfaces.modele;

import com.kasaflex.api.DTOs.modele.ModeleRequestDTO;
import com.kasaflex.api.DTOs.modele.ModeleResponseDTO;

import java.util.List;

public interface IModeleService {

    ModeleResponseDTO save(ModeleRequestDTO request);

    List<ModeleResponseDTO> findAll();

    ModeleResponseDTO findById(String idModele);

    ModeleResponseDTO update(ModeleRequestDTO request, String idModele);

    void delete(String idModele);
}
