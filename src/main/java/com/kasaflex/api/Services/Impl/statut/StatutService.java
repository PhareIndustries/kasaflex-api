package com.kasaflex.api.Services.Impl.statut;

import com.kasaflex.api.DTOs.statut.StatutRequestDTO;
import com.kasaflex.api.DTOs.statut.StatutResponseDTO;
import com.kasaflex.api.Entities.Statut;
import com.kasaflex.api.Mappers.StatutMapper;
import com.kasaflex.api.Repositories.statut.StatutRepository;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.statut.IStatutService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatutService implements IStatutService {
    private final StatutRepository statutRepository;
    private final AuthorizationService authorizationService;


    @Override
    @Transactional(readOnly = true)
    public StatutResponseDTO save(StatutRequestDTO dto) {
        authorizationService.ensureAdmin();

        Statut statut = new Statut();
        statut.setNomStatut(dto.getNomStatut());
        Statut saved = statutRepository.save(statut);
        StatutMapper map = new StatutMapper();
        return  map.toResponse(saved);
    }

    @Override
    public List<StatutResponseDTO> findAll() {
        StatutMapper statutMapper = new StatutMapper();
        return statutRepository.findAll()
                .stream()
                .map(statutMapper::toResponse)
                .toList();
    }

    @Override
    public StatutResponseDTO update(StatutRequestDTO item, String idStatut) {
        authorizationService.ensureAdmin();

        Statut statut = statutRepository.findById(idStatut)
                .orElseThrow(() -> new EntityNotFoundException("Statut introuvable : " + idStatut));
        statut.setNomStatut(item.getNomStatut());
        Statut updated = statutRepository.save(statut);
        StatutMapper mapper = new StatutMapper();
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public StatutResponseDTO findById(String idStatut) {
        Statut statut = statutRepository.findById(idStatut)
                .orElseThrow(() -> new EntityNotFoundException("Statut introuvable : " + idStatut));

        StatutMapper mapper = new StatutMapper();
        return mapper.toResponse(statut);
    }

    @Override
    @Transactional
    public void delete(String idStatut) {
        authorizationService.ensureAdmin();

        Statut statut = statutRepository.findById(idStatut)
                .orElseThrow(() -> new EntityNotFoundException("Statut introuvable : " + idStatut));

        statutRepository.delete(statut);
    }
}
