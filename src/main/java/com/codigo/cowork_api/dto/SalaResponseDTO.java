package com.codigo.cowork_api.dto;

public record SalaResponseDTO(
        Long id,
        String codigo,
        String nombre,
        Integer capacidad,
        String ubicacion,
        boolean activa,
        String mensajeL

) {
    // Java genera automáticamente el constructor con los 6 campos
    // y sus métodos de lectura: id(), codigo(), nombre(), capacidad(), ubicacion(), mensajeL()
}