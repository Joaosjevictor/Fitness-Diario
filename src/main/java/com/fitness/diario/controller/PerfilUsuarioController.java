package com.fitness.diario.controller;

import com.fitness.diario.model.PerfilUsuario;
import com.fitness.diario.service.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
@CrossOrigin(origins = "*")
public class PerfilUsuarioController {

    private final PerfilUsuarioService service;

    @Autowired
    public PerfilUsuarioController(PerfilUsuarioService service) {
        this.service = service;
    }

<<<<<<< HEAD
    // CREATE
    @PostMapping
    public ResponseEntity<PerfilUsuario> criarPerfil(@RequestBody PerfilUsuario usuario) {

        PerfilUsuario perfilGerado = service.criarPerfilEGerarPlano(usuario);

        return new ResponseEntity<>(perfilGerado, HttpStatus.CREATED);
    }

    // READ - LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<PerfilUsuario>> listarTodos() {

        return ResponseEntity.ok(service.listarTodos());
    }

    // READ - POR OBJETIVO
    @GetMapping("/objetivo/{objetivo}")
    public ResponseEntity<List<PerfilUsuario>> buscarPorObjetivo(
            @PathVariable String objetivo) {

        List<PerfilUsuario> perfis = service.buscarPorObjetivo(objetivo);

        return ResponseEntity.ok(perfis);
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<PerfilUsuario> atualizarPerfil(
            @RequestBody PerfilUsuario usuario) {

        PerfilUsuario atualizado = service.atualizarPerfil(usuario);

        return ResponseEntity.ok(atualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPerfil(
            @PathVariable String id) {

        service.deletarPerfil(id);

        return ResponseEntity.noContent().build();
    }
}
=======
    // Endpoint para criar o usuário e gerar a dieta automaticamente
    @PostMapping
    public ResponseEntity<PerfilUsuario> criarPerfil(@RequestBody PerfilUsuario usuario) {
        // O Controller recebe apenas as métricas básicas e repassa para o Service
        PerfilUsuario perfilGerado = service.criarPerfilEGerarPlano(usuario);
        return new ResponseEntity<>(perfilGerado, HttpStatus.CREATED);
    }

    // Endpoint para demonstrar a busca profunda do Couchbase
    @GetMapping("/objetivo/{objetivo}")
    public ResponseEntity<List<PerfilUsuario>> buscarPorObjetivo(@PathVariable String objetivo) {
        List<PerfilUsuario> perfis = service.buscarPorObjetivo(objetivo);
        return ResponseEntity.ok(perfis);
    }
}
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
