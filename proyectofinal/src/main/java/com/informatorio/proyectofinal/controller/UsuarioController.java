package com.informatorio.proyectofinal.controller;

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
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("idUsuario") Long id,
                                              @RequestBody @Valid Usuario usuario) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("El usuario no existe"));
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setCiudad(usuario.getCiudad());
        usuarioExistente.setProvincia(usuario.getProvincia());
        usuarioExistente.setPais(usuario.getPais());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());
        usuarioExistente.setActivo(usuario.getActivo());
        return new ResponseEntity(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("idUsuario") Long id) {
        usuarioRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/usuario", params = "ciudad")
    public ResponseEntity<?> obtenerPorCiudad(@RequestParam String ciudad) {
        return new ResponseEntity(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
    }

    @GetMapping(value = "/usuario", params = "fecha")
    public ResponseEntity<?> obtenerPorFecha(@RequestParam String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime fechaFormateada = LocalDate.parse(fecha, formatter).atStartOfDay();
        return new ResponseEntity(usuarioRepository.findByFechaCreacionAfter(fechaFormateada), HttpStatus.OK);
    }
}
