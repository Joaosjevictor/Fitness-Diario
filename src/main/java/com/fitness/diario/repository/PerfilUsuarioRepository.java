package com.fitness.diario.repository;


import com.fitness.diario.model.PerfilUsuario;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilUsuarioRepository extends CouchbaseRepository<PerfilUsuario, String> {

    // O Spring já resolve o básico (Save, FindById, Delete) automaticamente.
    
    // Consulta personalizada para buscar dentro do JSON (Prova do Documento)
    @Query("#{#n1ql.selectEntity} WHERE objetivo = $1")
    List<PerfilUsuario> findByObjetivo(String objetivo);
    
}
