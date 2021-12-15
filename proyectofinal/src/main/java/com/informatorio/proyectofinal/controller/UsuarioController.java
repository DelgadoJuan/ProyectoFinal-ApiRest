package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.UsuarioDto;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(@RequestParam(name = "ciudad", required = false) String ciudad,
                                          @RequestParam(name = "fecha", required = false) String fecha) {
        if (ciudad != null) {
            return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
        } else if (fecha != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime fechaFormateada = LocalDate.parse(fecha, formatter).atStartOfDay();
            return new ResponseEntity<>(usuarioRepository.findByFechaCreacionAfter(fechaFormateada), HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("idUsuario") Long id,
                                              @RequestBody @Valid UsuarioDto usuarioDto) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("El usuario no existe"));
        usuarioExistente.setNombre(usuarioDto.getNombre());
        usuarioExistente.setApellido(usuarioDto.getApellido());
        usuarioExistente.setEmail(usuarioDto.getEmail());
        usuarioExistente.setCiudad(usuarioDto.getCiudad());
        usuarioExistente.setProvincia(usuarioDto.getProvincia());
        usuarioExistente.setPais(usuarioDto.getPais());
        usuarioExistente.setPassword(usuarioDto.getPassword());
        usuarioExistente.setActivo(usuarioDto.getActivo());
        return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("idUsuario") Long id) {
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
