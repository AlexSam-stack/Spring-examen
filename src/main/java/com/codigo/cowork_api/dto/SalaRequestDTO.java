package com.codigo.cowork_api.dto;

public record SalaRequestDTO(
        String codigo,
        String nombre,
        Integer capacidad,
        String ubicacion
) {
    // Java genera automáticamente el constructor compacto,
    // los métodos de acceso: codigo(), nombre(), capacidad(), ubicacion()
    // y los métodos equals(), hashCode() y toString().
}