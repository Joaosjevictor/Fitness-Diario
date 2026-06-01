package com.fitness.diario.service;

import com.fitness.diario.model.PerfilUsuario;

public interface ObjetivoStrategy {
    // Retorna qual é o objetivo que esta estratégia atende
    String getObjetivo(); 
    
    // Método que vai calcular e preencher o plano no perfil do usuário
    void montarPlano(PerfilUsuario usuario);
}