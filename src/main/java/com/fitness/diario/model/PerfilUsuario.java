package com.fitness.diario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Collection("perfis")
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    private String nome;

    private double pesoAtual;

    private List<Double> historicoPesos;

    private String objetivo;

    private List<String> restricoesMedicas;

    private Map<String, PlanoDiario> planoSemanal;
}