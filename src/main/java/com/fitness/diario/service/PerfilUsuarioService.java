package com.fitness.diario.service;

import com.fitness.diario.model.PerfilUsuario;
import com.fitness.diario.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PerfilUsuarioService {

    private final PerfilUsuarioRepository repository;
    private final Map<String, ObjetivoStrategy> strategies;

    @Autowired
    public PerfilUsuarioService(PerfilUsuarioRepository repository, List<ObjetivoStrategy> strategyList) {
        this.repository = repository;

        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ObjetivoStrategy::getObjetivo, s -> s));
    }

    public PerfilUsuario criarPerfilEGerarPlano(PerfilUsuario usuario) {

        ObjetivoStrategy strategy = strategies.get(usuario.getObjetivo());

        ObjetivoStrategy strategy = strategies.get(usuario.getObjetivo());
        
        if (strategy == null) {
            throw new IllegalArgumentException("Objetivo não suportado: " + usuario.getObjetivo());
        }

        strategy.montarPlano(usuario);

        return repository.save(usuario);
    }

    public List<PerfilUsuario> buscarPorObjetivo(String objetivo) {
        return repository.findByObjetivo(objetivo);
    }

    public List<PerfilUsuario> listarTodos() {
        return repository.findAll();
    }

    public PerfilUsuario atualizarPerfil(PerfilUsuario usuario) {
        return repository.save(usuario);
    }

    public void deletarPerfil(String id) {
        repository.deleteById(id);
    }
}
        strategy.montarPlano(usuario);

        return repository.save(usuario);
    }

    public List<PerfilUsuario> buscarPorObjetivo(String objetivo) {
        return repository.findByObjetivo(objetivo);
    }
}
