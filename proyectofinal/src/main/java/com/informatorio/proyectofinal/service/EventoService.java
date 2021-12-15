package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Evento;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final EventoRepository eventoRepository;

    public EventoService(EmprendimientoRepository emprendimientoRepository, EventoRepository eventoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventoRepository = eventoRepository;
    }

    public List<Emprendimiento> ranking(Evento evento) {
        return emprendimientoRepository.findByEventoOrderByCantidadVotosDesc(evento);
    }
}
