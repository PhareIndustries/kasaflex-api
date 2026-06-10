package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.modele.ModeleResponseDTO;
import com.kasaflex.api.Entities.Modele;

public class ModeleMapper {

    public ModeleResponseDTO toResponse(Modele modele) {
        return new ModeleResponseDTO(
                modele.getIdModele(),
                modele.getNomModele(),
                modele.getNumeroModele()
        );
    }
}
