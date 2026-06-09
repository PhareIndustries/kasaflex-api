package com.kasaflex.api.Services.Impl.user;

import com.kasaflex.api.DTOs.user.UserRequestDTO;
import com.kasaflex.api.DTOs.user.UserResponseDTO;
import com.kasaflex.api.Entities.Role;
import com.kasaflex.api.Entities.User;
import com.kasaflex.api.Mappers.UserMapper;
import com.kasaflex.api.Repositories.role.RoleRepository;
import com.kasaflex.api.Repositories.user.UserRepository;
import com.kasaflex.api.Services.Interfaces.user.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDTO save(UserRequestDTO request) {
        if (!StringUtils.hasText(request.getMotDePasse())) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }

        Role role = findRole(request.getRole());

        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setMotDePasse(request.getMotDePasse());
        user.setRole(role);

        User saved = userRepository.save(user);
        return new UserMapper().toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        UserMapper mapper = new UserMapper();
        return userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findById(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUser));

        return new UserMapper().toResponse(user);
    }

    @Override
    @Transactional
    public UserResponseDTO update(UserRequestDTO request, String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUser));

        Role role = findRole(request.getRole());

        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setRole(role);

        if (StringUtils.hasText(request.getMotDePasse())) {
            user.setMotDePasse(request.getMotDePasse());
        }

        User updated = userRepository.save(user);
        return new UserMapper().toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + idUser));

        userRepository.delete(user);
    }

    private Role findRole(String idRole) {
        return roleRepository.findById(idRole)
                .orElseThrow(() -> new EntityNotFoundException("Rôle introuvable : " + idRole));
    }
}
