package com.codigo.cowork_api.service;

import com.codigo.cowork_api.dto.SalaRequestDTO;
import com.codigo.cowork_api.dto.SalaResponseDTO;
import com.codigo.cowork_api.mapper.SalaMapper;
import com.codigo.cowork_api.model.Sala;
import com.codigo.cowork_api.repository.ReservaRepository;
import com.codigo.cowork_api.repository.SalaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;



@Service
public class SalaService {
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public SalaService(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<SalaResponseDTO> listarSalas(){
    List<Sala> salas = salaRepository.listar();
    List<SalaResponseDTO> salasDTO = new ArrayList<>();

        for (Sala sala: salas) {
        SalaResponseDTO salaDTO = SalaMapper.toSalaResponseDto(sala, "Producto Listado Correctamente");
        salasDTO.add(salaDTO);
        }
        return salasDTO;
    }


    public SalaResponseDTO obtenerSalaPorId(Long id) {
        Sala sala = salaRepository.obtenerSalaPorId(id);

        // Una sola línea reemplaza 6 líneas de setters manuales
        return SalaMapper.toSalaResponseDto(sala,"Sala Agregada Correctamente");
    }


    public SalaResponseDTO crearSala(SalaRequestDTO requestDTO) {


        Sala sala = SalaMapper.toSala(requestDTO);
        Sala salaGuardada = salaRepository.crearNuevaSala(sala);

        // Paso 3: Entidad guardada (con ID) → DTO de respuesta
        return SalaMapper.toSalaResponseDto(
                salaGuardada, "Sala Guardada Correctamente"
        );
    }

    public SalaResponseDTO actualizarSala(Long id, SalaRequestDTO requestActualziarDTO) {
        Sala productoExistente = salaRepository.obtenerSalaPorId(id);

        if(productoExistente == null)  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el producto");
        }

        Sala salaActualizar = SalaMapper.toSala(requestActualziarDTO);
        salaActualizar.setId(id);

        Sala salaActualizado = salaRepository.actualizarSala(salaActualizar);

        return SalaMapper.toSalaResponseDto(salaActualizado,"Producto Actualizado correctamente");

    }


    public void eliminarSala(Long id) {
        // 1. Verificar que la sala existe
        Sala existente = salaRepository.obtenerSalaPorId(id);
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró la sala con ID: " + id);
        }

        // 2. Eliminar primero las reservas asociadas (cascada manual)
        reservaRepository.eliminarPorSalaId(id);

        // 3. Eliminar la sala
        salaRepository.eliminarSala(id);
    }
}


