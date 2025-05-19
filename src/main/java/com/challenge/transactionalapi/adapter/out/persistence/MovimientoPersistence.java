package com.challenge.transactionalapi.adapter.out.persistence;

import java.util.Date;
import java.util.List;
import com.challenge.transactionalapi.adapter.out.persistence.repository.MovimientoRepository;
import com.challenge.transactionalapi.application.port.out.MovimientoPersistencePort;
import com.challenge.transactionalapi.common.PersistenceAdapter;
import com.challenge.transactionalapi.domain.entity.Movimiento;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MovimientoPersistence implements MovimientoPersistencePort {
	
	private final MovimientoRepository repository;
	
	@Override
	public Movimiento save(Movimiento body) {
		return repository.save(body);
	}

	@Override
	public List<Movimiento> buscarPorFechaYUsuario(Date fechaInicio, Date fechaFin, Integer idUsuario) {
		return repository.buscarPorFechaYUsuario(fechaInicio, fechaFin, idUsuario);
	}

}
