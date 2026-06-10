package com.kasaflex.api.Services.Impl.role;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;
import com.kasaflex.api.Entities.Role;
import com.kasaflex.api.Mappers.RoleMapper;
import com.kasaflex.api.Repositories.role.RoleRepository;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.role.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final AuthorizationService authorizationService;

    @Override
    public RoleResponseDTO save(RoleRequestDTO dto) {
        authorizationService.ensureAdmin();

        Role role = roleMapper.toEntity(dto);
        Role savedRole = roleRepository.save(role);

        return roleMapper.toResponseDTO(savedRole);
    }

    @Override
    public List<RoleResponseDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public RoleResponseDTO getRoleById(String id) {
        authorizationService.ensureAdmin();

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rôle introuvable : " + id));

        return roleMapper.toResponseDTO(role);
    }

    @Override
    public RoleResponseDTO update(String id, RoleRequestDTO dto) {
        authorizationService.ensureAdmin();

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rôle introuvable : " + id));

        role.setNomRole(dto.getNomRole());
        Role updated = roleRepository.save(role);

        return roleMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(String id) {
        authorizationService.ensureAdmin();

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rôle introuvable : " + id));

        roleRepository.delete(role);
    }
}