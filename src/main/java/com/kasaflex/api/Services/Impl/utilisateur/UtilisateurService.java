package com.kasaflex.api.Services.Impl.utilisateur;

import com.kasaflex.api.DTOs.utilisateur.UtilisateurRequestDTO;
import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;
import com.kasaflex.api.Entities.Role;
import com.kasaflex.api.Entities.Utilisateur;
import com.kasaflex.api.Mappers.UtilisateurMapper;
import com.kasaflex.api.Repositories.role.RoleRepository;
import com.kasaflex.api.Repositories.utilisateur.UtilisateurRepository;
import com.kasaflex.api.Services.Interfaces.utilisateur.IUtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements IUtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UtilisateurResponseDTO save(UtilisateurRequestDTO request) {
        if (!StringUtils.hasText(request.getMotDePasse())) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }

        Role role = findRole(request.getRole());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(request.getMotDePasse());
        utilisateur.setRole(role);

        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return new UtilisateurMapper().toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilisateurResponseDTO> findAll() {
        UtilisateurMapper mapper = new UtilisateurMapper();
        return utilisateurRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UtilisateurResponseDTO findById(String idUser) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUser));

        return new UtilisateurMapper().toResponse(utilisateur);
    }

    @Override
    @Transactional
    public UtilisateurResponseDTO update(UtilisateurRequestDTO request, String idUser) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUser));

        Role role = findRole(request.getRole());

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(role);

        if (StringUtils.hasText(request.getMotDePasse())) {
            utilisateur.setMotDePasse(request.getMotDePasse());
        }

        Utilisateur updated = utilisateurRepository.save(utilisateur);
        return new UtilisateurMapper().toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String idUser) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUser));

        utilisateurRepository.delete(utilisateur);
    }

    private Role findRole(String idRole) {
        return roleRepository.findById(idRole)
                .orElseThrow(() -> new EntityNotFoundException("Rôle introuvable : " + idRole));
    }
}
