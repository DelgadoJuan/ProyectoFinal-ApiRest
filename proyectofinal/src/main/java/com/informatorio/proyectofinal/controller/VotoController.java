package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.VotoDto;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import com.informatorio.proyectofinal.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/voto")
public class VotoController {
    private final UsuarioRepository usuarioRepository;
    private final VotoService votoService;

    @Autowired
    public VotoController(UsuarioRepository usuarioRepository, VotoService votoService) {
        this.usuarioRepository = usuarioRepository;
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<?> votar(@RequestBody @Valid VotoDto votoDto) throws Exception {
        return votoService.crearVoto(votoDto);
    }

    @GetMapping(value = "/{usuarioId}")
    public ResponseEntity<?> obtenerVotosUsuario(@PathVariable("usuarioId") Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe ese usuario"));
        return new ResponseEntity<>(votoService.obtenerVotosUsuario(usuario), HttpStatus.OK);
    }
}
