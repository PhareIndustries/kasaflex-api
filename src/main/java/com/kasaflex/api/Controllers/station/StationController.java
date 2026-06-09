package com.kasaflex.api.Controllers.station;

import com.kasaflex.api.DTOs.station.StationRequestDTO;
import com.kasaflex.api.DTOs.station.StationResponseDTO;
import com.kasaflex.api.Services.Interfaces.station.IStationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final IStationService stationService;

    @PostMapping
    public ResponseEntity<StationResponseDTO> save(@Valid @RequestBody StationRequestDTO request) {
        StationResponseDTO response = stationService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<StationResponseDTO>> findAll() {
        return ResponseEntity.ok(stationService.findAll());
    }

    @PutMapping("/{idStation}")
    public ResponseEntity<StationResponseDTO> update(
            @PathVariable String idStation,
            @Valid @RequestBody StationRequestDTO request) {
        StationResponseDTO response = stationService.update(request, idStation);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idStation}")
    public ResponseEntity<StationResponseDTO> findById(@PathVariable String idStation) {
        StationResponseDTO response = stationService.findById(idStation);
        return ResponseEntity.ok(response);
    }
}
