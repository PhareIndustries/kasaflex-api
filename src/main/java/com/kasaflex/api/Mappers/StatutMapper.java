package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.statut.StatutResponseDTO;
import com.kasaflex.api.Entities.Statut;

public class StatutMapper {
    public StatutResponseDTO toResponse(Statut statut) {
        return new StatutResponseDTO(
                statut.getIdStatut(),
                statut.getNomStatut()
        );
    }
}
