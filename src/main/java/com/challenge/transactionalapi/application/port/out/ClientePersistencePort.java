package com.challenge.transactionalapi.application.port.out;

import java.util.Optional;
import com.challenge.transactionalapi.domain.entity.Usuario;

public interface ClientePersistencePort {
	
	Usuario save(Usuario body);
	
	Usuario findByIdentificacion(String Identificacion);
	
	Optional<Usuario> findById(Integer id);
}
