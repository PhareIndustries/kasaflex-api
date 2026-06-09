package com.kasaflex.api.Controllers.user;

import com.kasaflex.api.DTOs.user.UserRequestDTO;
import com.kasaflex.api.DTOs.user.UserResponseDTO;
import com.kasaflex.api.Services.Interfaces.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO response = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String idUser) {
        return ResponseEntity.ok(userService.findById(idUser));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable String idUser,
            @Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.update(request, idUser));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable String idUser) {
        userService.delete(idUser);
        return ResponseEntity.noContent().build();
    }
}
