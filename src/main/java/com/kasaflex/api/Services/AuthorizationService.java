package com.kasaflex.api.Services;

import com.kasaflex.api.Entities.User;
import com.kasaflex.api.Exceptions.AccessDeniedException;
import com.kasaflex.api.Repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private static final String ADMIN_ROLE = "ADMIN";

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void ensureAdmin(String userId) {
        if (!StringUtils.hasText(userId)) {
            throw new AccessDeniedException("Utilisateur non authentifié");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AccessDeniedException("Utilisateur non authentifié"));

        if (!ADMIN_ROLE.equalsIgnoreCase(user.getRole().getNomRole())) {
            throw new AccessDeniedException("Accès réservé aux administrateurs");
        }
    }

    @Transactional(readOnly = true)
    public void ensureCanUpdateClient(String userId, String clientIdHeader, String idClient) {
        if (isAdmin(userId)) {
            return;
        }

        if (StringUtils.hasText(clientIdHeader) && clientIdHeader.equals(idClient)) {
            return;
        }

        throw new AccessDeniedException(
                "Accès refusé : seul le client concerné ou un administrateur peut modifier ce compte");
    }

    private boolean isAdmin(String userId) {
        if (!StringUtils.hasText(userId)) {
            return false;
        }

        return userRepository.findById(userId)
                .map(user -> ADMIN_ROLE.equalsIgnoreCase(user.getRole().getNomRole()))
                .orElse(false);
    }
}
