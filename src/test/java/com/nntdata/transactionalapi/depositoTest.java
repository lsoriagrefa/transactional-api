package com.nntdata.transactionalapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Deposito;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.application.port.out.CuentaPersistencePort;
import com.challenge.transactionalapi.application.port.out.MovimientoPersistencePort;
import com.challenge.transactionalapi.application.service.ClienteService;
import com.challenge.transactionalapi.application.service.CuentaService;
import com.challenge.transactionalapi.application.service.MovimientoService;
import com.challenge.transactionalapi.domain.entity.Cuenta;
import com.challenge.transactionalapi.domain.entity.Movimiento;


@ExtendWith(MockitoExtension.class)
public class depositoTest {
	@Mock
	private CuentaPersistencePort cuentaPersistencePort;
	
	@Mock
	private MovimientoPersistencePort movimientoPersistencePort; 
	
	
	
	@Mock
	private ClientePersistencePort clientePersistencePort;
	
	@InjectMocks
	private ClienteService clienteService;
	
	
	@InjectMocks
	private CuentaService cuentaService;
	
	@InjectMocks
	private MovimientoService movimientoService;
	
	
	@Test
	void sinCuentaTest() throws Exception{
		
		Cuenta cuenta = new Cuenta();
		cuenta.setId(1);
		cuenta.setNumeroCuenta("001");
		cuenta.setIdUsuario(1);
		cuenta.setIdTipoCuenta(1); 
		cuenta.setSaldo(new BigDecimal("1000.00")); 
		cuenta.setEstado(true);
		cuenta.setFCreacion(new Date()); 
		cuenta.setFActualizacion(null); 
		cuenta.setUCreacion("admin");
		cuenta.setUActualizacion(null);
		
		Cuenta cuentaResponse = new Cuenta();
		cuenta.setId(1);
		cuenta.setNumeroCuenta("001");
		cuenta.setIdUsuario(1);
		cuenta.setIdTipoCuenta(1); 
		cuenta.setSaldo(new BigDecimal("1100.00")); 
		cuenta.setEstado(true);
		cuenta.setFCreacion(new Date()); 
		cuenta.setFActualizacion(null); 
		cuenta.setUCreacion("admin");
		cuenta.setUActualizacion(null);
		
		Deposito deposito = new Deposito();
		deposito.setNumeroCuenta("001");
		deposito.setValor(new BigDecimal(1000));
		
		Movimiento m1 = new Movimiento();
        m1.setIdCuenta(1);
        m1.setIdTipoMovimiento(1); // Depósito
        m1.setFecha(new Date());
        m1.setValor(new BigDecimal("100.00"));
        m1.setSaldo(new BigDecimal("1100.00"));
        m1.setFCreacion(new Date());
        m1.setFActualizacion(null);
        m1.setUCreacion("admin");
        m1.setUActualizacion(null);
		
		when(cuentaPersistencePort.findCuentaByNumeroCuenta(any(String.class))).thenReturn(Optional.empty());
		
		ResponseEntity<ApiResponse<?>> movimientoResponse = movimientoService.deposito(deposito);
		assertEquals("NUMERO DE CUENTA NO EXISTE", movimientoResponse.getBody().getMessage());
	}
}
