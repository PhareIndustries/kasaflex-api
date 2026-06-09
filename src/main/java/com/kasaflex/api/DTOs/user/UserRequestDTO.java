package com.kasaflex.api.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    private String nom;

    private String prenom;

    @NotBlank
    @Email
    private String email;

    private String motDePasse;

    @NotBlank
    private String role;
}
