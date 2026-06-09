package com.kasaflex.api.Services.Impl.auth;

import com.kasaflex.api.DTOs.auth.LoginRequestDTO;
import com.kasaflex.api.DTOs.auth.LoginResponseDTO;
import com.kasaflex.api.Entities.User;
import com.kasaflex.api.Exceptions.UnauthorizedException;
import com.kasaflex.api.Repositories.user.UserRepository;
import com.kasaflex.api.Services.Interfaces.auth.IAuthService;
import com.kasaflex.api.Services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Email ou mot de passe incorrect"));

        if (!user.getMotDePasse().equals(request.getMotDePasse())) {
            throw new UnauthorizedException("Email ou mot de passe incorrect");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(
                user.getIdUser(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getRole().getIdRole(),
                user.getRole().getNomRole(),
                token
        );
    }
}
