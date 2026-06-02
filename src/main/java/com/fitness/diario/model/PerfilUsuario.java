package com.fitness.diario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;
import lombok.Data;
<<<<<<< HEAD

=======
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
import java.util.List;
import java.util.Map;

@Data
@Document
<<<<<<< HEAD
@Collection("perfis")
=======
@Collection("perfis") // Onde os documentos serão salvos dentro do Bucket
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String nome;

    @Field
    private double pesoAtual;

<<<<<<< HEAD
    // NOVO CAMPO PARA DEMONSTRAR ESTRUTURA DOCUMENTAL
    @Field
    private List<Double> historicoPesos;

    @Field
    private String objetivo;

    @Field
    private List<String> restricoesMedicas;

    @Field
    private Map<String, PlanoDiario> planoSemanal;
}
=======
    @Field
    private String objetivo; // Ex: "Hipertrofia", "Emagrecimento"

    @Field
    private List<String> restricoesMedicas; // Ex: ["Diabetes", "Lesão no Joelho"]

   
    @Field
    private Map<String, PlanoDiario> planoSemanal; 
}
>>>>>>> 1a1069683e19badb36346655baba389fe39f3f87
