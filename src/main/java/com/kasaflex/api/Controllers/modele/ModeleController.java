package com.kasaflex.api.Controllers.modele;

import com.kasaflex.api.DTOs.modele.ModeleRequestDTO;
import com.kasaflex.api.DTOs.modele.ModeleResponseDTO;
import com.kasaflex.api.Services.Interfaces.modele.IModeleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modeles")
@RequiredArgsConstructor
public class ModeleController {

    private final IModeleService modeleService;

    @PostMapping
    public ResponseEntity<ModeleResponseDTO> save(@Valid @RequestBody ModeleRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modeleService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<ModeleResponseDTO>> findAll() {
        return ResponseEntity.ok(modeleService.findAll());
    }

    @GetMapping("/{idModele}")
    public ResponseEntity<ModeleResponseDTO> findById(@PathVariable String idModele) {
        return ResponseEntity.ok(modeleService.findById(idModele));
    }

    @PutMapping("/{idModele}")
    public ResponseEntity<ModeleResponseDTO> update(
            @PathVariable String idModele,
            @Valid @RequestBody ModeleRequestDTO request) {
        return ResponseEntity.ok(modeleService.update(request, idModele));
    }

    @DeleteMapping("/{idModele}")
    public ResponseEntity<Void> delete(@PathVariable String idModele) {
        modeleService.delete(idModele);
        return ResponseEntity.noContent().build();
    }
}
