package com.challenge.transactionalapi.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import com.challenge.transactionalapi.adapter.out.persistence.repository.CuentaRepository;
import com.challenge.transactionalapi.application.port.out.CuentaPersistencePort;
import com.challenge.transactionalapi.common.PersistenceAdapter;
import com.challenge.transactionalapi.domain.entity.Cuenta;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CuentaPersistence implements CuentaPersistencePort{
	
	private final CuentaRepository repository;

	@Override
	public Cuenta save(Cuenta body) {
		return repository.save(body);
	}

	@Override
	public List<Cuenta> findCuentasByIdUsuario(Integer idUsuario) {
		return repository.findCuentasByIdUsuario(idUsuario);
	}

	@Override
	public Optional<Cuenta> findCuentaByNumeroCuenta(String numeroCuenta) {
		return repository.findCuentaByNumeroCuenta(numeroCuenta);
	}

	@Override
	public Optional<Cuenta> findCuentaById(Integer idCuenta) {
		return repository.findCuentaById(idCuenta);
	}

}
