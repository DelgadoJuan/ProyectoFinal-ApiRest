package com.informatorio.proyectofinal.converter;

import com.informatorio.proyectofinal.dto.VotoDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import com.informatorio.proyectofinal.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class VoteDtoToVotoEntityConverter implements Converter<VotoDto, Voto> {

    private final UsuarioRepository usuarioRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public VoteDtoToVotoEntityConverter(UsuarioRepository usuarioRepository,
                                        EmprendimientoRepository emprendimientoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @Override
    public Voto convert(VotoDto votoDto) {
        Voto voto = new Voto();
        voto.setUsuario(usuarioRepository.getById(votoDto.getUsuarioId()));
        voto.setGeneradoPor(votoDto.getGeneradoPor());
        Emprendimiento emprendimiento = emprendimientoRepository.getById(votoDto.getEmprendimientoId());
        emprendimiento.setCantidadVotos(emprendimiento.getCantidadVotos() + 1);
        emprendimiento.agregarVoto(voto);
        return voto;
    }
}