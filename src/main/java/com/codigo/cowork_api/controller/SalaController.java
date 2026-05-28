package com.codigo.cowork_api.controller;

import com.codigo.cowork_api.dto.SalaRequestDTO;
import com.codigo.cowork_api.dto.SalaResponseDTO;
import com.codigo.cowork_api.service.SalaService;
import jakarta.validation.Valid; //
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    // GET /api/salas – 200 OK
    @GetMapping
    public List<SalaResponseDTO> listarTodas() {
        return salaService.listarSalas();
    }

    // GET /api/salas/{id} – 200 OK
    @GetMapping("/{id}")
    public SalaResponseDTO obtenerPorId(@PathVariable Long id) {
        return salaService.obtenerSalaPorId(id);
    }

    // POST /api/salas – 201 Created
    @PostMapping
    public ResponseEntity<SalaResponseDTO> crear(@Valid @RequestBody SalaRequestDTO request) { // <-- Se agregó @Valid
        SalaResponseDTO nueva = salaService.crearSala(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PUT /api/salas/{id} – 200 OK
    @PutMapping("/{id}")
    public SalaResponseDTO actualizar(@PathVariable Long id,
                                      @Valid @RequestBody SalaRequestDTO request) { // <-- Se agregó @Valid para proteger la edición
        return salaService.actualizarSala(id, request);
    }

    // DELETE /api/salas/{id} – 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }
}