package com.kasaflex.api.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthContext {

    private final String id;
    private final String mail;
    private final String role;
    private final String type;
}
