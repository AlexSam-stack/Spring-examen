package com.codigo.cowork_api.repository;


import com.codigo.cowork_api.model.Reserva;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private Long contadoId=105L;
    public ReservaRepository() {

        reservas.add(new Reserva(
                101L,
                1L,
                "Carlos Mendoza",
                "carlos.mendoza@empresa.com",
                LocalDate.of(2026, 6, 1),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                "CONFIRMADA",
                null // passwordInterno omitido por seguridad
        ));

        // Reserva 2: Sala Andes - Pendiente
        reservas.add(new Reserva(
                102L,
                1L,
                "Ana Gómez",
                "ana.gomez@empresa.com",
                LocalDate.of(2026, 6, 1),
                LocalTime.of(14, 0),
                LocalTime.of(15, 30),
                "PENDIENTE",
                "AG-102"
        ));

        // Reserva 3: Sala Amazonas - Confirmada
        reservas.add(new Reserva(
                103L,
                2L,
                "Luis Castro",
                "luis.castro@empresa.com",
                LocalDate.of(2026, 6, 2),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                "CONFIRMADA",
                "LC-103"
        ));

        // Reserva 4: Sala Pacífico - Cancelada
        reservas.add(new Reserva(
                104L,
                3L,
                "Sofía Reyes",
                "sofia.reyes@empresa.com",
                LocalDate.of(2026, 6, 2),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                "CANCELADA",
                "SR-104"
        ));
    }

    public List<Reserva> listar(){
        return reservas;
    }

    public Reserva buscarReservaPorId(Long id){
        for (Reserva reserva: reservas) {
            if (reserva.getId().equals(id)) { // .equals() compara valor, no referencia
                return reserva;
            }
        }
        return null;
    }

    public Reserva crearReservaacion(Reserva reserva){
        reserva.setId(contadoId++);
        reserva.setEstado("CONFIRMADA");
       reservas.add(reserva);
        return reserva;
    }

    public Reserva actualizarReserva(Reserva reserva){
        for(Reserva reservaActualizada  : reservas){
            if(reservaActualizada.getId().equals(reserva.getId())){
                reservaActualizada.setEmail(reserva.getEmail());
                reservaActualizada.setEstado(reserva.getEstado());
                reservaActualizada.setFecha(reserva.getFecha());
                reservaActualizada.setHoraFin(reserva.getHoraFin());
                reservaActualizada.setHoraInicio(reserva.getHoraInicio());
                reservaActualizada.setResponsable(reserva.getResponsable());
                reservaActualizada.setSalaId(reserva.getSalaId());
                reservaActualizada.setPasswordInterno(reserva.getPasswordInterno());
                return reservaActualizada;
            }
        }
        return null;
    }

    public void eliminarReserva(long id){
        Reserva reserva = buscarReservaPorId(id);
        reservas.remove(reserva);
    }

    public void eliminarPorSalaId(Long salaId) {
        reservas.removeIf(r -> r.getSalaId().equals(salaId));
    }


}
