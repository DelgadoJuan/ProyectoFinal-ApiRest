package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.dto.VotoDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import com.informatorio.proyectofinal.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Objects;

@Service
public class VotoService {
    private final Converter<VotoDto, Voto> votoDtoVotoConverter;
    private final VotoRepository votoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public VotoService(Converter<VotoDto, Voto> votoDtoVotoConverter, VotoRepository votoRepository, UsuarioRepository usuarioRepository, EmprendimientoRepository emprendimientoRepository) {
        this.votoDtoVotoConverter = votoDtoVotoConverter;
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    public ResponseEntity<?> crearVoto(VotoDto votoDto) throws Exception {
        Usuario usuario = usuarioRepository.findById(votoDto.getUsuarioId())
                .orElseThrow(() -> new Exception("No existe ese usuario."));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(votoDto.getEmprendimientoId())
                .orElseThrow(() -> new Exception("No existe ese emprendimiento."));
        for (Voto votos: votoRepository.findAll()) {
            if (usuario == votos.getUsuario() && emprendimiento == votos.getEmprendimiento()) {
                return new ResponseEntity<>(new Exception("Este usuario ya votó este emprendimiento"),
                        HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
        Voto voto = votoDtoVotoConverter.convert(votoDto);
        return new ResponseEntity<>(votoRepository.save(Objects.requireNonNull(voto)), HttpStatus.CREATED);
    }

    public List<Voto> obtenerVotosUsuario(Usuario usuario) {
        return votoRepository.findByUsuario(usuario);
    }
}
