package com.fitness.diario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Document
@Collection("perfis") // Onde os documentos serão salvos dentro do Bucket
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String nome;

    @Field
    private double pesoAtual;

    @Field
    private String objetivo; // Ex: "Hipertrofia", "Emagrecimento"

    @Field
    private List<String> restricoesMedicas; // Ex: ["Diabetes", "Lesão no Joelho"]

   
    @Field
    private Map<String, PlanoDiario> planoSemanal; 
}
