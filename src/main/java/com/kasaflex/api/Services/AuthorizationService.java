package com.kasaflex.api.Services;

import com.kasaflex.api.Entities.Utilisateur;
import com.kasaflex.api.Exceptions.AccessDeniedException;
import com.kasaflex.api.Repositories.utilisateur.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private static final String ADMIN_ROLE = "ADMIN";

    private final UtilisateurRepository utilisateurRepository;

        if (!JwtService.TYPE_USER.equals(context.getType())) {
            throw new AccessDeniedException("Utilisateur non authentifié");
        }

        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new AccessDeniedException("Utilisateur non authentifié"));

        if (!ADMIN_ROLE.equalsIgnoreCase(utilisateur.getRole().getNomRole())) {
            throw new AccessDeniedException("Accès réservé aux administrateurs");
        }
    }

    public void ensureCanUpdateClient(String idClient) {
        AuthContext context = AuthContextHolder.get()
                .orElseThrow(() -> new AccessDeniedException("Authentification requise"));

        if (JwtService.TYPE_USER.equals(context.getType())
                && ADMIN_ROLE.equalsIgnoreCase(context.getRole())) {
            return;
        }

        if (JwtService.TYPE_CLIENT.equals(context.getType())
                && context.getId().equals(idClient)) {
            return;
        }

        throw new AccessDeniedException(
                "Accès refusé : seul le client concerné ou un administrateur peut modifier ce compte");
    }

    private boolean isAdmin(String userId) {
        if (!StringUtils.hasText(userId)) {
            return false;
        }

        return utilisateurRepository.findById(userId)
                .map(utilisateur -> ADMIN_ROLE.equalsIgnoreCase(utilisateur.getRole().getNomRole()))
                .orElse(false);
    }
}
