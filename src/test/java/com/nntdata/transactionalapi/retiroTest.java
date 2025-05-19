package com.nntdata.transactionalapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Retiro;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.application.port.out.CuentaPersistencePort;
import com.challenge.transactionalapi.application.port.out.MovimientoPersistencePort;
import com.challenge.transactionalapi.application.service.ClienteService;
import com.challenge.transactionalapi.application.service.CuentaService;
import com.challenge.transactionalapi.application.service.MovimientoService;
import com.challenge.transactionalapi.domain.entity.Cuenta;
import com.challenge.transactionalapi.domain.entity.Movimiento;

@ExtendWith(MockitoExtension.class)
public class retiroTest {
	
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
	void retiroTest() throws Exception{
		
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
		
		Retiro retiro = new Retiro();
		retiro.setNumeroCuenta("001");
		retiro.setValor(new BigDecimal(100));
		
		Movimiento m1 = new Movimiento();
        m1.setIdCuenta(1);
        m1.setIdTipoMovimiento(2); 
        m1.setFecha(new Date());
        m1.setValor(new BigDecimal("100.00"));
        m1.setSaldo(new BigDecimal("1100.00"));
        m1.setFCreacion(new Date());
        m1.setFActualizacion(null);
        m1.setUCreacion("admin");
        m1.setUActualizacion(null);
		
        when(movimientoPersistencePort.save(any(Movimiento.class))).thenReturn(m1);
		when(cuentaPersistencePort.findCuentaByNumeroCuenta(any(String.class))).thenReturn(Optional.of(cuenta));
		when(cuentaPersistencePort.save(any(Cuenta.class))).thenReturn(cuentaResponse);
		movimientoService.retiro(retiro);
		
		ArgumentCaptor<Cuenta> captor = ArgumentCaptor.forClass(Cuenta.class);
		verify(cuentaPersistencePort, times(1)).save(captor.capture());
		Cuenta cuentaActualizada = captor.getValue();
		
		assertEquals(new BigDecimal("1000.00"), cuentaActualizada.getSaldo());
	}

}
