package com.kasaflex.api.Services.Interfaces.role;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;

import java.util.List;

public interface IRoleService {

    RoleResponseDTO save(RoleRequestDTO role, String userId);

    List<RoleResponseDTO> findAll();

    RoleResponseDTO getRoleById(String id);

    void delete(String id, String userId);
}
