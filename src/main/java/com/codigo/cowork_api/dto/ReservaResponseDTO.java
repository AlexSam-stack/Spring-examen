package com.codigo.cowork_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaResponseDTO(
        Long id,
        Long salaId,
        String responsable,

        @Email
        String email,

        @JsonFormat(pattern = "yyyy-MM-dd") // Corregido 'mm' por 'MM' para el mes
        LocalDate fecha,

        @JsonFormat(pattern = "HH:mm")
        LocalTime horaInicio,

        @JsonFormat(pattern = "HH:mm")
        LocalTime horaFin,

        @JsonIgnore
        String passwordInterno,

        String consulta,
        String estado
) {
    // Java genera de forma automática el constructor completo y los métodos de lectura:
    // id(), salaId(), responsable(), email(), fecha(), horaInicio(), horaFin(), passwordInterno(), consulta()
}