package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.entity.Evento;
import com.informatorio.proyectofinal.repository.EventoRepository;
import com.informatorio.proyectofinal.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {

    private final EventoRepository eventoRepository;
    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoRepository eventoRepository, EventoService eventoService) {
        this.eventoRepository = eventoRepository;
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<?> crearEvento(@RequestBody @Valid Evento evento) {
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{idEvento}")
    public ResponseEntity<?> modificarEvento(@PathVariable("idEvento") Long id,
                                             @RequestBody @Valid Evento evento) throws Exception {
        Evento eventoExistente = eventoRepository.findById(id)
                .orElseThrow(() -> new Exception("El evento no existe"));
        eventoExistente.setDetalle(evento.getDetalle());
        eventoExistente.setPremio(evento.getPremio());
        eventoExistente.setFechaCierre(evento.getFechaCierre());
        eventoExistente.setEstado(evento.getEstado());
        return new ResponseEntity<>(eventoRepository.save(eventoExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idEvento}")
    public ResponseEntity<?> eliminarEvento(@PathVariable("idEvento") Long id) {
        eventoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{idEvento}/ranking")
    public ResponseEntity<?> obtenerRanking(@PathVariable("idEvento") Long id) throws Exception {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new Exception("El evento no existe"));
        return new ResponseEntity<>(eventoService.ranking(evento), HttpStatus.OK);
    }
}
