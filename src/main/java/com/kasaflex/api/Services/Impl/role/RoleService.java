package com.kasaflex.api.Services.Impl.role;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;
import com.kasaflex.api.Entities.Role;
import com.kasaflex.api.Mappers.RoleMapper;
import com.kasaflex.api.Repositories.role.RoleRepository;
import com.kasaflex.api.Services.AuthorizationService;
import com.kasaflex.api.Services.Interfaces.role.IRoleService;
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
    public RoleResponseDTO save(RoleRequestDTO dto, String userId) {
        authorizationService.ensureAdmin(userId);

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
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role introuvable"));

        return roleMapper.toResponseDTO(role);
    }

    @Override
    public void delete(String id, String userId) {
        authorizationService.ensureAdmin(userId);

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Role introuvable"));

        roleRepository.delete(role);
    }
}