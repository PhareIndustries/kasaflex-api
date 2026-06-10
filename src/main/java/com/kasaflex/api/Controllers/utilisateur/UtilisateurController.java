package com.kasaflex.api.Controllers.utilisateur;

import com.kasaflex.api.DTOs.utilisateur.UtilisateurRequestDTO;
import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;
import com.kasaflex.api.Services.Interfaces.utilisateur.IUtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UtilisateurController {

    private final IUtilisateurService userService;

    @PostMapping
    public ResponseEntity<UtilisateurResponseDTO> save(@Valid @RequestBody UtilisateurRequestDTO request) {
        UtilisateurResponseDTO response = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UtilisateurResponseDTO> findById(@PathVariable String idUser) {
        return ResponseEntity.ok(userService.findById(idUser));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UtilisateurResponseDTO> update(
            @PathVariable String idUser,
            @Valid @RequestBody UtilisateurRequestDTO request) {
        return ResponseEntity.ok(userService.update(request, idUser));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable String idUser) {
        userService.delete(idUser);
        return ResponseEntity.noContent().build();
    }
}
