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
<<<<<<< HEAD

=======
        // Transforma a lista de estratégias em um Mapa para busca super rápida (O(1))
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ObjetivoStrategy::getObjetivo, s -> s));
    }

    public PerfilUsuario criarPerfilEGerarPlano(PerfilUsuario usuario) {
<<<<<<< HEAD

        ObjetivoStrategy strategy = strategies.get(usuario.getObjetivo());

=======
        // 1. Busca a estratégia correta baseada no objetivo do usuário
        ObjetivoStrategy strategy = strategies.get(usuario.getObjetivo());
        
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
        if (strategy == null) {
            throw new IllegalArgumentException("Objetivo não suportado: " + usuario.getObjetivo());
        }

<<<<<<< HEAD
        strategy.montarPlano(usuario);

        return repository.save(usuario);
    }

    public List<PerfilUsuario> buscarPorObjetivo(String objetivo) {
        return repository.findByObjetivo(objetivo);
    }

    // NOVOS MÉTODOS

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
=======
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
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
