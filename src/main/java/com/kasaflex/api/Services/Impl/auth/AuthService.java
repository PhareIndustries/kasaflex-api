package com.kasaflex.api.Services.Impl.auth;

import com.kasaflex.api.DTOs.auth.LoginRequestDTO;
import com.kasaflex.api.DTOs.auth.LoginResponseDTO;
import com.kasaflex.api.Entities.Utilisateur;
import com.kasaflex.api.Exceptions.UnauthorizedException;
import com.kasaflex.api.Repositories.utilisateur.UtilisateurRepository;
import com.kasaflex.api.Services.Interfaces.auth.IAuthService;
import com.kasaflex.api.Services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO request) {
        Utilisateur user = utilisateurRepository.findByMail(request.getMail())
                .orElseThrow(() -> new UnauthorizedException("Mail ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasse())) {
            throw new UnauthorizedException("Mail ou mot de passe incorrect");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(
                user.getIdUtilisateur(),
                user.getNom(),
                user.getPrenom(),
                user.getMail(),
                user.getRole().getIdRole(),
                user.getRole().getNomRole(),
                token
        );
    }
}
