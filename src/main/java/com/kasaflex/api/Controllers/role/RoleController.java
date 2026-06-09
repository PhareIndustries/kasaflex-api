package com.kasaflex.api.Controllers.role;

import com.kasaflex.api.DTOs.role.RoleRequestDTO;
import com.kasaflex.api.DTOs.role.RoleResponseDTO;
import com.kasaflex.api.Services.Interfaces.role.IRoleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin("*")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleResponseDTO createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        return roleService.save(roleRequestDTO);
    }

    @GetMapping
    public List<RoleResponseDTO> getAllRoles() {
        return roleService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {

        roleService.delete(id);

        return ResponseEntity.noContent().build();
    }
}