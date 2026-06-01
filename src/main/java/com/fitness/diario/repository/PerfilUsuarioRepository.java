package com.fitness.diario.repository;


import com.fitness.diario.model.PerfilUsuario;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilUsuarioRepository extends CouchbaseRepository<PerfilUsuario, String> {

    @Query("#{#n1ql.selectEntity} WHERE objetivo = $1")
    List<PerfilUsuario> findByObjetivo(String objetivo);
    
}
