package com.challenge.transactionalapi.application.port.out;

import java.util.List;
import java.util.Optional;
import com.challenge.transactionalapi.domain.entity.Cuenta;

public interface CuentaPersistencePort {
	
	Cuenta save(Cuenta body);
	
    List<Cuenta> findCuentasByIdUsuario(Integer idUsuario);
    
    Optional<Cuenta> findCuentaByNumeroCuenta(String numeroCuenta);
    
    Optional<Cuenta> findCuentaById(Integer idCuenta);
}
