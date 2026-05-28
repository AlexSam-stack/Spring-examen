package com.codigo.cowork_api.mapper;

import com.codigo.cowork_api.dto.SalaRequestDTO;
import com.codigo.cowork_api.dto.SalaResponseDTO;
import com.codigo.cowork_api.model.Sala;

public class SalaMapper {
    public static Sala toSala(SalaRequestDTO salaRequestDTO) {
        if (salaRequestDTO == null) {
            return null;
        }

        Sala sala = new Sala();
        // Acceso a los atributos del record usando la sintaxis de función ()
        sala.setCodigo(salaRequestDTO.codigo());
        sala.setNombre(salaRequestDTO.nombre());
        sala.setCapacidad(salaRequestDTO.capacidad());
        sala.setUbicacion(salaRequestDTO.ubicacion());
        sala.setActiva(true);

        return sala;
    }

    // Convierte de Entidad a DTO de Salida (Record)
    public static SalaResponseDTO toSalaResponseDto(Sala sala, String mensaje) {
        if (sala == null) {
            return null;
        }

        // Instanciamos el record SalaResponseDTO pasando todos los parámetros ordenados
        return new SalaResponseDTO(
                sala.getId(),
                sala.getCodigo(),
                sala.getNombre(),
                sala.getCapacidad(),
                sala.getUbicacion(),
                sala.isActiva(),
                mensaje // Mapeamos el parámetro 'mensaje' en el campo 'mensajeL' del record
        );
    }
}
