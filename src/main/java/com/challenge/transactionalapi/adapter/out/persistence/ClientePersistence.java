package com.challenge.transactionalapi.adapter.out.persistence;

import java.util.Optional;

import com.challenge.transactionalapi.adapter.out.persistence.repository.ClienteRepository;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.common.PersistenceAdapter;
import com.challenge.transactionalapi.domain.entity.Usuario;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ClientePersistence implements ClientePersistencePort{
	
	private final ClienteRepository repository;

	@Override
	public Usuario findByIdentificacion(String identificacion) {
		return repository.findByIdentificacion(identificacion);
	}

	@Override
	public Usuario save(Usuario body) {
		return repository.save(body);
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		return repository.findUsuarioById(id);
	}

}
