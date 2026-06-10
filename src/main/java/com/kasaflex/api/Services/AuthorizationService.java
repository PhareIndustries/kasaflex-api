package com.kasaflex.api.Services;

import com.kasaflex.api.Exceptions.AccessDeniedException;
import com.kasaflex.api.Security.AuthContext;
import com.kasaflex.api.Security.AuthContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private static final String ADMIN_ROLE = "ADMIN";

    public void ensureAdmin() {
        AuthContext context = requireAuth();

        if (!JwtService.TYPE_USER.equals(context.getType())) {
            throw new AccessDeniedException("Utilisateur non authentifié");
        }

        if (!ADMIN_ROLE.equalsIgnoreCase(context.getRole())) {
            throw new AccessDeniedException("Accès réservé aux administrateurs");
        }
    }

    public void ensureCanUpdateClient(String idClient) {
        AuthContext context = requireAuth();

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

    private AuthContext requireAuth() {
        return AuthContextHolder.get()
                .orElseThrow(() -> new AccessDeniedException("Authentification requise"));
    }
}
