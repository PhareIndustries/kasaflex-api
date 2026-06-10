package com.kasaflex.api.Controllers.statut;

import com.kasaflex.api.DTOs.statut.StatutRequestDTO;
import com.kasaflex.api.DTOs.statut.StatutResponseDTO;
import com.kasaflex.api.Services.Interfaces.statut.IStatutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statut")
@RequiredArgsConstructor
public class StatutController {
    private final IStatutService statutService;

    @PostMapping
    public ResponseEntity<StatutResponseDTO> save(@Valid @RequestBody StatutRequestDTO request) {
        StatutResponseDTO response = statutService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{idStatut}")
    public ResponseEntity<StatutResponseDTO> update(@PathVariable String idStatut,@Valid @RequestBody StatutRequestDTO request) {
        StatutResponseDTO response = statutService.update(request, idStatut);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idStatut}")
    public ResponseEntity<StatutResponseDTO> findById(@PathVariable String idStatut) {
        StatutResponseDTO response = statutService.findById(idStatut);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idStatut}")
    public ResponseEntity<Void> delete(@PathVariable String idStatut) {
        statutService.delete(idStatut);
        return ResponseEntity.noContent().build();
    }
}
