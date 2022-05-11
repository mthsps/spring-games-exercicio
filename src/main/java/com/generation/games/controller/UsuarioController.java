package com.generation.games.controller;

import com.generation.games.model.Usuario;
import com.generation.games.model.UsuarioLogin;
import com.generation.games.repository.UsuarioRepository;
import com.generation.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

     @Autowired
     private UserService userService;

     @Autowired
     private UsuarioRepository usuarioRepository;

     @GetMapping
     public ResponseEntity<List<Usuario>> getAll() {
         return ResponseEntity.ok(usuarioRepository.findAll());
     }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> user) {
         return userService.authenticateUser(user).map(ResponseEntity::ok)
                 .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()); }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> postUser(@Valid @RequestBody Usuario usuario) {
         return userService.register(usuario)
                 .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                 .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
     }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

}
