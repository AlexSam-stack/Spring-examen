package com.codigo.cowork_api.repository;

import com.codigo.cowork_api.model.Sala;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong; // Requerido para R2

@Repository
public class SalaRepository {

    private final List<Sala> salas = new ArrayList<>();

    // R2: Se cambia el Long primitivo/objeto por AtomicLong iniciando en 5 (ya que quemamos del 1 al 4)
    private final AtomicLong contadorId = new AtomicLong(5);

    public SalaRepository() {
        // Datos iniciales quemados en memoria
        salas.add(new Sala(1L, "SALA-A1", "Sala Andes", 15, "Piso 3", true));
        salas.add(new Sala(2L, "SALA-B2", "Sala Amazonas", 8, "Piso 2", true));
        salas.add(new Sala(3L, "SALA-C1", "Sala Pacífico", 25, "Piso 4", true));
        salas.add(new Sala(4L, "SALA-M1", "Cabina Misti", 4, "Piso 1", false));
    }

    public List<Sala> listar() {
        return salas;
    }

    public Sala obtenerSalaPorId(Long id) {
        for (Sala sala : salas) {
            if (sala.getId().equals(id)) {
                return sala;
            }
        }
        return null;
    }

    public Sala crearNuevaSala(Sala sala) {
        // .getAndIncrement() incrementa el ID de forma atómica y segura
        sala.setId(contadorId.getAndIncrement());
        salas.add(sala);
        return sala;
    }

    public Sala actualizarSala(Sala sala) {
        for (Sala salaActualizada : salas) {
            if (salaActualizada.getId().equals(sala.getId())) {
                salaActualizada.setActiva(sala.isActiva());
                salaActualizada.setCapacidad(sala.getCapacidad());
                salaActualizada.setCodigo(sala.getCodigo());
                salaActualizada.setNombre(sala.getNombre());
                salaActualizada.setUbicacion(sala.getUbicacion());
                return salaActualizada;
            }
        }
        return null;
    }

    public void eliminarSala(Long id) {
        Sala sala = obtenerSalaPorId(id);
        if (sala != null) {
            salas.remove(sala);
        }
    }
}