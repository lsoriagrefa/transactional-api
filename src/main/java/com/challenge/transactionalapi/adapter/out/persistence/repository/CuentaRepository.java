package com.challenge.transactionalapi.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.challenge.transactionalapi.domain.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{
	
    @Query("SELECT c FROM Cuenta c WHERE c.estado=true AND c.idUsuario = :idUsuario")
    List<Cuenta> findCuentasByIdUsuario(@Param("idUsuario") int idUsuario);
    
    @Query("SELECT c FROM Cuenta c WHERE c.estado = true AND c.numeroCuenta = :numeroCuenta")
    Optional<Cuenta> findCuentaByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);
    
    @Query("SELECT c FROM Cuenta c WHERE c.estado = true AND c.id = :id")
    Optional<Cuenta> findCuentaById(@Param("id") int id);

}
