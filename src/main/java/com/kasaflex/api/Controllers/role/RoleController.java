package com.kasaflex.api.Controllers.role;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;
import com.kasaflex.api.Services.Interfaces.role.IRoleService;
import jakarta.validation.Valid;
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
    public RoleResponseDTO createRole(@Valid @RequestBody RoleRequestDTO roleRequestDTO) {
        return roleService.save(roleRequestDTO);
    }

    @GetMapping
    public List<RoleResponseDTO> getAllRoles() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleResponseDTO getRoleById(@PathVariable String id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public RoleResponseDTO updateRole(
            @PathVariable String id,
            @Valid @RequestBody RoleRequestDTO roleRequestDTO) {
        return roleService.update(id, roleRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
