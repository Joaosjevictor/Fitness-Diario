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
