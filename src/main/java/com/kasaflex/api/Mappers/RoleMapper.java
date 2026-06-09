package com.kasaflex.api.Mappers;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;
import com.kasaflex.api.Entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequestDTO dto) {

        Role role = new Role();
        role.setNomRole(dto.getNomRole());

        return role;
    }

    public RoleResponseDTO toResponseDTO(Role role) {

        RoleResponseDTO dto = new RoleResponseDTO();

        dto.setIdRole(role.getIdRole());
        dto.setNomRole(role.getNomRole());

        return dto;
    }
}
