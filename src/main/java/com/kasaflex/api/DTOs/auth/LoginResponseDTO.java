package com.kasaflex.api.DTOs.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String idUser;
    private String nom;
    private String prenom;
    private String email;
    private String idRole;
    private String nomRole;
    private String token;
}
