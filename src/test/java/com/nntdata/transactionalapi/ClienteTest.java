package com.nntdata.transactionalapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCliente;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.application.service.ClienteService;
import com.challenge.transactionalapi.domain.entity.Usuario;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {
	@Mock
	private ClientePersistencePort clientePersistencePort;
	@Mock
	private PasswordEncoder passwordEncoder;
	@InjectMocks
	private ClienteService clienteService;
	
	@Test
	void depositoTest() throws Exception{
		
		CrearCliente clienteNuevo = new CrearCliente();
		clienteNuevo.setIdentificacion("1234567890");
		clienteNuevo.setNombres("Juan Carlos");
		clienteNuevo.setApellidos("Pérez Gómez");
		clienteNuevo.setSexo("M");
		clienteNuevo.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1990-05-10"));
		clienteNuevo.setDireccion("Av. Principal 123");
		clienteNuevo.setTelefono("0987654321");
		when(clientePersistencePort.save(any(Usuario.class))).thenReturn(any(Usuario.class));
		clienteService.registrarCliente(clienteNuevo);
		
		verify(clientePersistencePort, times(1)).save(any(Usuario.class));
	}
}
