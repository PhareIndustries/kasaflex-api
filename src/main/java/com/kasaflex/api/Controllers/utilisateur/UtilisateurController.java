package com.kasaflex.api.Controllers.utilisateur;

import com.kasaflex.api.DTOs.utilisateur.OnCreate;
import com.kasaflex.api.DTOs.utilisateur.UtilisateurRequestDTO;
import com.kasaflex.api.DTOs.utilisateur.UtilisateurResponseDTO;
import com.kasaflex.api.Services.Interfaces.utilisateur.IUtilisateurService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<UtilisateurResponseDTO> save(
            @Validated({OnCreate.class, Default.class}) @RequestBody UtilisateurRequestDTO request) {
        UtilisateurResponseDTO response = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<UtilisateurResponseDTO> findById(@PathVariable String idUtilisateur) {
        return ResponseEntity.ok(userService.findById(idUtilisateur));
    }

    @PutMapping("/{idUtilisateur}")
    public ResponseEntity<UtilisateurResponseDTO> update(
            @PathVariable String idUtilisateur,
            @Valid @RequestBody UtilisateurRequestDTO request) {
        return ResponseEntity.ok(userService.update(request, idUtilisateur));
    }

    @DeleteMapping("/{idUtilisateur}")
    public ResponseEntity<Void> delete(@PathVariable String idUtilisateur) {
        userService.delete(idUtilisateur);
        return ResponseEntity.noContent().build();
    }
}
