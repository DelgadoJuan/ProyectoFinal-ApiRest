package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.dto.EmprendimientoDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Tags;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.TagsRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmprendimientoService {
    private final Converter<EmprendimientoDto, Emprendimiento> emprendimientoDtoEmprendimientoConverter;
    private final EmprendimientoRepository emprendimientoRepository;
    private final TagsRepository tagsRepository;

    public EmprendimientoService(Converter<EmprendimientoDto, Emprendimiento> emprendimientoDtoEmprendimientoConverter,
                                 EmprendimientoRepository emprendimientoRepository, TagsRepository tagsRepository) {
        this.emprendimientoDtoEmprendimientoConverter = emprendimientoDtoEmprendimientoConverter;
        this.emprendimientoRepository = emprendimientoRepository;
        this.tagsRepository = tagsRepository;
    }

    public boolean crearEmprendimiento(EmprendimientoDto emprendimientoDto) {
        Emprendimiento emprendimiento = emprendimientoDtoEmprendimientoConverter.convert(emprendimientoDto);
        emprendimientoRepository.save(Objects.requireNonNull(emprendimiento));
        return true;
    }

    public List<Emprendimiento> obtenerTodos() {
        return emprendimientoRepository.findAll();
    }

    public List<Emprendimiento> obtenerPorTag(String tag) {
        return emprendimientoRepository.findByTag(tag);
    }

    public List<Emprendimiento> obtenerNoPublicados() {
        return emprendimientoRepository.findByPublicadoFalse();
    }

    public void agregarTag(String nombre, Long id) throws Exception {
        Emprendimiento emprendimiento = emprendimientoRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe ese emprendimiento"));
        Tags tag = new Tags();
        tag.setNombre(nombre);
        emprendimiento.agregarTag(tag);
        tagsRepository.save(tag);
    }
}
