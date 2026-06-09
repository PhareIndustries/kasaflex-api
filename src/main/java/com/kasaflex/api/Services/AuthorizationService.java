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
}
