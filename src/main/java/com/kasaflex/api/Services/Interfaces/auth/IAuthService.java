package com.kasaflex.api.Services.Interfaces.auth;

import com.kasaflex.api.DTOs.auth.LoginRequestDTO;
import com.kasaflex.api.DTOs.auth.LoginResponseDTO;

public interface IAuthService {

    LoginResponseDTO login(LoginRequestDTO request);
}
