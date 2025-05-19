package com.challenge.transactionalapi.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.challenge.transactionalapi.domain.entity.Movimiento;
import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	@Query(value = "SELECT m.* FROM CASHINOUT.dbo.MOVIMIENTO m " +
            "JOIN CASHINOUT.dbo.CUENTA c ON m.IDCUENTA = c.ID " +
            "WHERE CAST(m.FECHA AS DATE) BETWEEN :fechaInicio AND :fechaFin " +
            "AND c.IDUSUARIO = :idUsuario",
            nativeQuery = true)
	List<Movimiento> buscarPorFechaYUsuario(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("idUsuario") Integer idUsuario);
}