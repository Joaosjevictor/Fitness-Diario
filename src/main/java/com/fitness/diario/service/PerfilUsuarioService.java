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
        // Transforma a lista de estratégias em um Mapa para busca super rápida (O(1))
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ObjetivoStrategy::getObjetivo, s -> s));
    }

    public PerfilUsuario criarPerfilEGerarPlano(PerfilUsuario usuario) {
        // 1. Busca a estratégia correta baseada no objetivo do usuário
        ObjetivoStrategy strategy = strategies.get(usuario.getObjetivo());
        
        if (strategy == null) {
            throw new IllegalArgumentException("Objetivo não suportado: " + usuario.getObjetivo());
        }

        // 2. Aplica a regra de negócio comportamental
        strategy.montarPlano(usuario);

        // 3. Salva o documento completo gerado no Couchbase
        return repository.save(usuario);
    }
    
    // Método para demonstrar as buscas internas no documento
    public List<PerfilUsuario> buscarPorObjetivo(String objetivo) {
        return repository.findByObjetivo(objetivo);
    }
}
