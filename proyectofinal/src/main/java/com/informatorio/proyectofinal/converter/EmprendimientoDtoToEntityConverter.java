package com.informatorio.proyectofinal.converter;

import com.informatorio.proyectofinal.dto.EmprendimientoDto;
import com.informatorio.proyectofinal.dto.Estado;
import com.informatorio.proyectofinal.dto.TipoUsuario;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Evento;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventoRepository;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class EmprendimientoDtoToEntityConverter implements Converter<EmprendimientoDto, Emprendimiento> {
    private EventoRepository eventoRepository;
    private UsuarioRepository usuarioRepository;
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoDtoToEntityConverter(EventoRepository eventoRepository,
                                              UsuarioRepository usuarioRepository,
                                              EmprendimientoRepository emprendimientoRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @Override
    public Emprendimiento convert(EmprendimientoDto emprendimientoDto) {
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(emprendimientoDto.getNombre());
        emprendimiento.setDescripcion(emprendimientoDto.getDescripcion());
        emprendimiento.setContenido(emprendimientoDto.getContenido());
        emprendimiento.setObjetivo(emprendimientoDto.getObjetivo());
        emprendimiento.setPublicado(emprendimientoDto.getPublicado());
        emprendimiento.setCreador(usuarioRepository.getById(emprendimientoDto.getUsuarioId()));
        if (emprendimiento.getCreador().getTipoUsuario() != TipoUsuario.OWNER) {
            emprendimiento.getCreador().setTipoUsuario(TipoUsuario.OWNER);
        }
        Evento evento = eventoRepository.getById(emprendimientoDto.getEventoId());
        evento.agregarEmprendimiento(emprendimiento);
        return emprendimiento;
    }
}
