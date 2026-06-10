package com.kasaflex.api.DTOs.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {

    @NotBlank(message = "Le nom du rôle est obligatoire")
    private String nomRole;
}
