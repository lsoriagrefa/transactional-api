package com.challenge.transactionalapi.application.port.out;

import java.util.Date;
import com.challenge.transactionalapi.domain.entity.Movimiento;
import java.util.List;

public interface MovimientoPersistencePort {
	
	Movimiento save(Movimiento body);
	
	List<Movimiento> buscarPorFechaYUsuario(Date fechaInicio, Date fechaFin, Integer idUsuario);
	

}
