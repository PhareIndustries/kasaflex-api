package com.kasaflex.api.Controllers.role;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;
import com.kasaflex.api.Services.Interfaces.role.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @PostMapping
    public RoleResponseDTO createRole(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody RoleRequestDTO roleRequestDTO) {
        return roleService.save(roleRequestDTO, userId);
    }

    @GetMapping
    public List<RoleResponseDTO> getAllRoles() {
        return roleService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String id) {
        roleService.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}
