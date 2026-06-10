package com.kasaflex.api.Services.Interfaces.utilisateur;

import com.kasaflex.api.DTOs.utilisateur.UtilisateurRequestDTO;
import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurResponseDTO save(UtilisateurRequestDTO request);

    List<UtilisateurResponseDTO> findAll();

    UtilisateurResponseDTO findById(String idUser);

    UtilisateurResponseDTO update(UtilisateurRequestDTO request, String idUser);

    void delete(String idUser);
}
