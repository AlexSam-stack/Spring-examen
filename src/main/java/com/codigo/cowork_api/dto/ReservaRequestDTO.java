package com.codigo.cowork_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaRequestDTO(
        Long salaId,
        String responsable,

        @Email
        String email,

        @JsonFormat(pattern = "yyyy-MM-dd") // Corregido 'mm' por 'MM' para meses
        LocalDate fecha,

        @JsonFormat(pattern = "HH:mm")
        LocalTime horaInicio,

        @JsonFormat(pattern = "HH:mm")
        LocalTime horaFin,

        @JsonIgnore
        String passwordInterno
) {
    // Al ser un record, Java genera automáticamente:
    // - El constructor principal con todos los campos.
    // - Los métodos de lectura (se acceden como dto.salaId() en lugar de dto.getSalaId()).
    // - Los métodos equals(), hashCode() y toString().
}