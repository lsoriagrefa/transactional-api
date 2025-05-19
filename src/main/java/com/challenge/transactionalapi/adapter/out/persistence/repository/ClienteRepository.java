package com.challenge.transactionalapi.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.challenge.transactionalapi.domain.entity.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Usuario, Integer>{
	
    @Query("SELECT u FROM Usuario u WHERE u.estado=true AND u.identificacion = :identificacion")
    Usuario findByIdentificacion(@Param("identificacion") String identificacion);
    
    @Query("SELECT u FROM Usuario u WHERE u.estado=true AND u.id = :id")
    Optional<Usuario> findUsuarioById(@Param("id") int id);
}
