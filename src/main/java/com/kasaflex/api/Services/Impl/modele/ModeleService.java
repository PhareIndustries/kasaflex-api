package com.kasaflex.api.Services.Impl.modele;

import com.kasaflex.api.DTOs.modele.ModeleRequestDTO;
import com.kasaflex.api.DTOs.modele.ModeleResponseDTO;
import com.kasaflex.api.Entities.Modele;
import com.kasaflex.api.Mappers.ModeleMapper;
import com.kasaflex.api.Repositories.modele.ModeleRepository;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.modele.IModeleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeleService implements IModeleService {

    private final ModeleRepository modeleRepository;
    private final AuthorizationService authorizationService;

    @Override
    @Transactional
    public ModeleResponseDTO save(ModeleRequestDTO request) {
        authorizationService.ensureAdmin();

        Modele modele = new Modele();
        modele.setNomModele(request.getNomModele());
        modele.setNumeroModele(request.getNumeroModele());

        Modele saved = modeleRepository.save(modele);
        return new ModeleMapper().toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModeleResponseDTO> findAll() {
        authorizationService.ensureAdmin();

        ModeleMapper mapper = new ModeleMapper();
        return modeleRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ModeleResponseDTO findById(String idModele) {
        authorizationService.ensureAdmin();

        Modele modele = modeleRepository.findById(idModele)
                .orElseThrow(() -> new EntityNotFoundException("Modèle introuvable : " + idModele));

        return new ModeleMapper().toResponse(modele);
    }

    @Override
    @Transactional
    public ModeleResponseDTO update(ModeleRequestDTO request, String idModele) {
        authorizationService.ensureAdmin();

        Modele modele = modeleRepository.findById(idModele)
                .orElseThrow(() -> new EntityNotFoundException("Modèle introuvable : " + idModele));

        modele.setNomModele(request.getNomModele());
        modele.setNumeroModele(request.getNumeroModele());

        Modele updated = modeleRepository.save(modele);
        return new ModeleMapper().toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String idModele) {
        authorizationService.ensureAdmin();

        Modele modele = modeleRepository.findById(idModele)
                .orElseThrow(() -> new EntityNotFoundException("Modèle introuvable : " + idModele));

        modeleRepository.delete(modele);
    }
}
