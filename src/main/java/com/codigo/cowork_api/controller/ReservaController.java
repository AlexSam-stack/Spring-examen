package com.codigo.cowork_api.controller;

import com.codigo.cowork_api.dto.ReservaRequestDTO;
import com.codigo.cowork_api.dto.ReservaResponseDTO;
import com.codigo.cowork_api.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // POST /api/reservas – 201 Created
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crear(@Valid @RequestBody ReservaRequestDTO request) { // <-- Se agregó @Valid aquí
        ReservaResponseDTO nueva = reservaService.crearReserva(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // GET /api/reservas/{id} – 200 OK
    @GetMapping("/{id}")
    public ReservaResponseDTO obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerReservaPorId(id);
    }

    // GET /api/reservas?estado=&fecha=&salaId= – 200 OK (filtros opcionales combinables)
    @GetMapping
    public List<ReservaResponseDTO> listarConFiltros(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) Long salaId) {
        return reservaService.listarConFiltros(estado, fecha, salaId);
    }

    // GET /api/reservas/sala/{salaId} – 200 OK
    @GetMapping("/sala/{salaId}")
    public List<ReservaResponseDTO> listarPorSala(@PathVariable Long salaId) {
        return reservaService.listarPorSala(salaId);
    }

    // PUT /api/reservas/{id}/estado?nuevoEstado= – 200 OK
    @PutMapping("/{id}/estado")
    public ReservaResponseDTO cambiarEstado(@PathVariable Long id,
                                            @RequestParam String nuevoEstado) {
        return reservaService.cambiarEstado(id, nuevoEstado);
    }

    // DELETE /api/reservas/{id} – 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/reservas/{id}/comprobante – 200 OK
    @PostMapping("/{id}/comprobante")
    public Map<String, Object> subirComprobante(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestHeader("X-Cliente-Id") String clienteId) {
        return Map.of(
                "nombreArchivo", Objects.requireNonNull(archivo.getOriginalFilename()),
                "tamanoBytes", archivo.getSize(),
                "clienteId", clienteId,
                "reservaId", id
        );
    }
}