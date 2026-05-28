package com.codigo.cowork_api.mapper;

import com.codigo.cowork_api.dto.ReservaRequestDTO;
import com.codigo.cowork_api.dto.ReservaResponseDTO;
import com.codigo.cowork_api.model.Reserva;

public class ReservaMapper {

    public static Reserva toReserva(ReservaRequestDTO reservaRequestDTO) {

        if (reservaRequestDTO == null) {
            return null;
        }
        Reserva reserva = new Reserva();
        reserva.setSalaId(reservaRequestDTO.salaId());
        reserva.setResponsable(reservaRequestDTO.responsable());
        reserva.setEmail(reservaRequestDTO.email());
        reserva.setFecha(reservaRequestDTO.fecha());
        reserva.setHoraInicio(reservaRequestDTO.horaInicio());
        reserva.setHoraFin(reservaRequestDTO.horaFin());
        reserva.setPasswordInterno(reservaRequestDTO.passwordInterno());
        return reserva;
    }
    public static ReservaResponseDTO tReservaResponseDto(Reserva reserva, String mensaje) {
        if (reserva == null) {
            return null;
        }

        // Como el DTO es un record, lo instanciamos pasando todos los datos en su constructor único
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getSalaId(),
                reserva.getResponsable(),
                reserva.getEmail(),
                reserva.getFecha(),
                reserva.getHoraInicio(),
                reserva.getHoraFin(),
                reserva.getPasswordInterno(), // Se envía null en passwordInterno para que jamás viaje el dato sensible en la respuesta
                mensaje,
                reserva.getEstado()// Mapeamos el parámetro 'mensaje' en el campo 'consulta' del record
        );
    }
}
