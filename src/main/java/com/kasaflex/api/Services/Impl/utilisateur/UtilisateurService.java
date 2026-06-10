package com.kasaflex.api.Services.Impl.utilisateur;

import com.kasaflex.api.DTOs.utilisateur.UtilisateurRequestDTO;
import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;
import com.kasaflex.api.Entities.Role;
import com.kasaflex.api.Entities.Utilisateur;
import com.kasaflex.api.Mappers.UtilisateurMapper;
import com.kasaflex.api.Repositories.role.RoleRepository;
import com.kasaflex.api.Repositories.utilisateur.UtilisateurRepository;
import com.kasaflex.api.Exceptions.AccessDeniedException;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.utilisateur.IUtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements IUtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationService authorizationService;

    @Override
    @Transactional
    public UtilisateurResponseDTO save(UtilisateurRequestDTO request) {
        authorizationService.ensureAdmin();

        if (utilisateurRepository.findByMail(request.getMail()).isPresent()) {
            throw new IllegalArgumentException("Cet mail est déjà utilisé");
        }

        Role role = findRole(request.getIdRole());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setMail(request.getMail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole(role);

        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return new UtilisateurMapper().toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilisateurResponseDTO> findAll() {
        boolean includePassword = authorizationService.isAdmin();
        UtilisateurMapper mapper = new UtilisateurMapper();
        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateur -> mapper.toResponse(utilisateur, includePassword))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UtilisateurResponseDTO findById(String idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUtilisateur));

        return new UtilisateurMapper().toResponse(utilisateur, authorizationService.isAdmin());
    }

    @Override
    @Transactional
    public UtilisateurResponseDTO update(UtilisateurRequestDTO request, String idUtilisateur) {
        authorizationService.ensureCanUpdateUtilisateur(idUtilisateur);

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUtilisateur));

        Role role = resolveRoleForUpdate(request, utilisateur);

        utilisateurRepository.findByMail(request.getMail())
                .filter(existing -> !existing.getIdUtilisateur().equals(idUtilisateur))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Cet mail est déjà utilisé");
                });

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setMail(request.getMail());
        utilisateur.setRole(role);

        if (StringUtils.hasText(request.getMotDePasse())) {
            utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        }

        Utilisateur updated = utilisateurRepository.save(utilisateur);
        return new UtilisateurMapper().toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String idUtilisateur) {
        authorizationService.ensureAdmin();
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUtilisateur));

        utilisateurRepository.delete(utilisateur);
    }

    private Role resolveRoleForUpdate(UtilisateurRequestDTO request, Utilisateur utilisateur) {
        if (authorizationService.isAdmin()) {
            return findRole(request.getIdRole());
        }

        if (StringUtils.hasText(request.getIdRole())
                && !request.getIdRole().equals(utilisateur.getRole().getIdRole())) {
            throw new AccessDeniedException("Seuls les administrateurs peuvent modifier le rôle");
        }

        return utilisateur.getRole();
    }

    private Role findRole(String idRole) {
        if (!StringUtils.hasText(idRole)) {
            throw new IllegalArgumentException("Le rôle est obligatoire");
        }
        return roleRepository.findById(idRole)
                .orElseThrow(() -> new EntityNotFoundException("Rôle introuvable : " + idRole));
    }
}
