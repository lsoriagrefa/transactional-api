package com.nntdata.transactionalapi;

import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Deposito;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.application.port.out.CuentaPersistencePort;
import com.challenge.transactionalapi.application.port.out.MovimientoPersistencePort;
import com.challenge.transactionalapi.application.service.ClienteService;
import com.challenge.transactionalapi.application.service.CuentaService;
import com.challenge.transactionalapi.application.service.MovimientoService;
import com.challenge.transactionalapi.domain.entity.Cuenta;
import com.challenge.transactionalapi.domain.entity.Movimiento;
import com.challenge.transactionalapi.domain.entity.Usuario;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionalapiApplicationTests {
	  
	
	
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
	void depositoTest() throws Exception{
		Usuario usuario =  new Usuario();
		usuario.setId(1);
		usuario.setIdentificacion("0503695264");
		usuario.setNombres("Juan Carlos");
		usuario.setApellidos("Pérez Gómez");
		usuario.setClave("123456"); 
		usuario.setSexo("M");
		usuario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1990-05-11"));
		usuario.setDireccion("Av. Siempre Viva 742");
		usuario.setTelefono("0999999999");
		usuario.setEstado(true);
		usuario.setFCreacion(new Date());
		usuario.setFActualizacion(null);
		usuario.setUCreacion("admin");
		
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
		
		when(movimientoPersistencePort.save(any(Movimiento.class))).thenReturn(m1);
		when(cuentaPersistencePort.findCuentaByNumeroCuenta(any(String.class))).thenReturn(Optional.of(cuenta));
		when(cuentaPersistencePort.save(any(Cuenta.class))).thenReturn(cuentaResponse);
		
		movimientoService.deposito(deposito);
		
		verify(movimientoPersistencePort, times(1)).save(any(Movimiento.class));
		verify(cuentaPersistencePort, times(1)).findCuentaByNumeroCuenta(any(String.class));
		verify(cuentaPersistencePort, times(1)).save(any(Cuenta.class));
		
	}
	
}
