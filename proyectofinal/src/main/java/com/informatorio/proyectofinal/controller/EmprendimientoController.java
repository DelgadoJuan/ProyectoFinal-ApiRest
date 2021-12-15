package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.EmprendimientoDto;
import com.informatorio.proyectofinal.dto.Estado;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Evento;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventoRepository;
import com.informatorio.proyectofinal.service.EmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/emprendimiento")
@RestController
public class EmprendimientoController {
    private final EmprendimientoService emprendimientoService;
    private final EventoRepository eventoRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoService emprendimientoService, EventoRepository eventoRepository,
                                    EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoService = emprendimientoService;
        this.eventoRepository = eventoRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@RequestBody @Valid EmprendimientoDto emprendimientoDto)
            throws Exception {
        Evento evento = eventoRepository.findById(emprendimientoDto.getEventoId())
                .orElseThrow(() -> new Exception("No existe el evento"));
        if (evento.getEstado() != Estado.ABIERTO) {
            return new ResponseEntity<>(new Exception("No se puede inscribir a ese evento"),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(emprendimientoService.crearEmprendimiento(emprendimientoDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(@RequestParam(name = "tag", required = false) String tag,
                                          @RequestParam(name = "publicado", required = false, defaultValue = "false")
                                                  Boolean publicado) {
        if (tag != null) {
            return new ResponseEntity<>(emprendimientoRepository.findByTag(tag), HttpStatus.OK);
        } else if (publicado) {
            return new ResponseEntity<>(emprendimientoService.obtenerNoPublicados(), HttpStatus.OK);
        }
        return new ResponseEntity<>(emprendimientoService.obtenerTodos(), HttpStatus.OK);
    }

    @PutMapping(value = "/{emprendimientoId}/tag")
    public ResponseEntity<?> agregarTag(@RequestBody String nombre,
                                        @PathVariable("emprendimientoId") Long id) throws Exception {
        emprendimientoService.agregarTag(nombre, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{emprendimientoId}")
    public ResponseEntity<?> modificarEmprendimiento(@PathVariable("emprendimientoId") Long id,
                                                     @RequestBody @Valid EmprendimientoDto emprendimientoDto)
            throws Exception {
        Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe ese evento."));
        emprendimientoExistente.setNombre(emprendimientoDto.getNombre());
        emprendimientoExistente.setDescripcion(emprendimientoDto.getDescripcion());
        emprendimientoExistente.setContenido(emprendimientoDto.getContenido());
        emprendimientoExistente.setObjetivo(emprendimientoDto.getObjetivo());
        emprendimientoExistente.setPublicado(emprendimientoDto.getPublicado());
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimientoExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{emprendimientoId}")
    public ResponseEntity<?> eliminarEmprendimiento(@PathVariable("emprendimientoId") Long id) throws Exception {
        Emprendimiento emprendimiento = emprendimientoRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe ese emprendimiento."));
        emprendimientoRepository.delete(emprendimiento);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
