package com.kasaflex.api.Controllers.client;

import com.kasaflex.api.DTOs.client.ClientRequestDTO;
import com.kasaflex.api.DTOs.client.ClientResponseDTO;
import com.kasaflex.api.Services.Interfaces.client.IClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> save(
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody ClientRequestDTO request) {
        ClientResponseDTO response = clientService.save(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable String idClient) {
        return ResponseEntity.ok(clientService.findById(idClient));
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<ClientResponseDTO> update(
            @PathVariable String idClient,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-Client-Id", required = false) String clientIdHeader,
            @Valid @RequestBody ClientRequestDTO request) {
        return ResponseEntity.ok(clientService.update(request, idClient, userId, clientIdHeader));
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> delete(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String idClient) {
        clientService.delete(idClient, userId);
        return ResponseEntity.noContent().build();
    }
}
