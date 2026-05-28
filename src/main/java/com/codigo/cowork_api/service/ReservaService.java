package com.codigo.cowork_api.service;

import com.codigo.cowork_api.dto.ReservaRequestDTO;
import com.codigo.cowork_api.dto.ReservaResponseDTO;
import com.codigo.cowork_api.mapper.ReservaMapper;
import com.codigo.cowork_api.model.Reserva;
import com.codigo.cowork_api.repository.ReservaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaResponseDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.listar();
        List<ReservaResponseDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaResponseDTO reservaDTO = ReservaMapper.tReservaResponseDto(reserva, "Reserva Listada Correctamente");
            reservasDTO.add(reservaDTO);
        }
        return reservasDTO;
    }

    public ReservaResponseDTO obtenerReservaPorId(Long id) {
        Reserva reserva = reservaRepository.buscarReservaPorId(id);

        if (reserva == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la reserva");
        }

        return ReservaMapper.tReservaResponseDto(reserva, "Reserva Obtenida Correctamente");
    }

    public ReservaResponseDTO crearReserva(ReservaRequestDTO requestDTO) {
        // Paso 1 y 2: DTO de Entrada -> Entidad -> Guardar en repositorio
        Reserva reserva = ReservaMapper.toReserva(requestDTO);

        // Por defecto al crear una reserva, se podría guardar con estado "PENDIENTE"
        reserva.setEstado("PENDIENTE");

        Reserva reservaGuardada = reservaRepository.crearReservaacion(reserva);

        // Paso 3: Entidad guardada (con ID) -> DTO de respuesta
        return ReservaMapper.tReservaResponseDto(
                reservaGuardada, "Reserva Guardada Correctamente"
        );
    }

    public ReservaResponseDTO actualizarReserva(Long id, ReservaRequestDTO requestActualizarDTO) {
        Reserva reservaExistente = reservaRepository.buscarReservaPorId(id);

        if (reservaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la reserva");
        }

        // Convertimos el DTO a entidad y le asignamos el ID del path variable
        Reserva reservaActualizar = ReservaMapper.toReserva(requestActualizarDTO);
        reservaActualizar.setId(id);

        // Mantenemos el estado que ya tenía o puedes manejarlo según tu lógica
        reservaActualizar.setEstado(reservaExistente.getEstado());

        Reserva reservaActualizada = reservaRepository.actualizarReserva(reservaActualizar);

        return ReservaMapper.tReservaResponseDto( reservaActualizada,"Reserva Actualizada correctamente");
    }

    public void eliminarReserva(Long id) {
        Reserva reservaExistente = reservaRepository.buscarReservaPorId(id);
        if (reservaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la reserva a eliminar");
        }
        reservaRepository.eliminarReserva(id);
    }


    public List<ReservaResponseDTO> listarConFiltros(String estado, LocalDate fecha, Long salaId) {
        return reservaRepository.listar()
                .stream()
                .filter(r -> estado == null || r.getEstado().equalsIgnoreCase(estado))
                .filter(r -> fecha == null  || r.getFecha().equals(fecha))
                .filter(r -> salaId == null || r.getSalaId().equals(salaId))
                .map(r -> ReservaMapper.tReservaResponseDto(r, "Reserva listada correctamente"))
                .collect(Collectors.toList());
    }

    /**
     * Lista todas las reservas que pertenecen a una sala específica.
     */
    public List<ReservaResponseDTO> listarPorSala(Long salaId) {
        return reservaRepository.listar()
                .stream()
                .filter(r -> r.getSalaId().equals(salaId))
                .map(r -> ReservaMapper.tReservaResponseDto(r, "Reserva listada correctamente"))
                .collect(Collectors.toList());
    }

    /**
     * R6 - Regla 8: solo se aceptan PENDIENTE, CONFIRMADA o CANCELADA.
     * Cualquier otro valor lanza RuntimeException con mensaje claro.
     */
    public ReservaResponseDTO cambiarEstado(Long id, String nuevoEstado) {
        // Validación del estado permitido
        if (!nuevoEstado.equals("PENDIENTE")
                && !nuevoEstado.equals("CONFIRMADA")
                && !nuevoEstado.equals("CANCELADA")) {
            throw new RuntimeException(
                    "Estado inválido: '" + nuevoEstado + "'. " +
                            "Los valores permitidos son: PENDIENTE, CONFIRMADA, CANCELADA."
            );
        }

        Reserva existente = reservaRepository.buscarReservaPorId(id);
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró la reserva con ID: " + id);
        }

        existente.setEstado(nuevoEstado);
        Reserva actualizada = reservaRepository.actualizarReserva(existente);
        return ReservaMapper.tReservaResponseDto(actualizada, "Estado actualizado correctamente");
    }

}
