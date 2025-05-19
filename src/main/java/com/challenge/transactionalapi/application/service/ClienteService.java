package com.challenge.transactionalapi.application.service;

import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCliente;
import com.challenge.transactionalapi.adapter.in.web.dto.request.ModificarCliente;
import java.util.Date;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.challenge.transactionalapi.adapter.in.web.dto.request.BuscarPorIdentificacion;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ClienteResponse;
import com.challenge.transactionalapi.application.port.in.ClientePortIn;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.common.UseCase;
import com.challenge.transactionalapi.config.Util;
import com.challenge.transactionalapi.config._CONST;
import com.challenge.transactionalapi.config.logger.Logger;
import com.challenge.transactionalapi.domain.entity.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@UseCase
public class ClienteService implements ClientePortIn {
	
	private final ClientePersistencePort persistencePort;
	private final PasswordEncoder passwordEncoder;
	private final Logger logger = new Logger();
	private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	
	public ClienteService(ClientePersistencePort persistencePort, PasswordEncoder passwordEncoder) {
		this.persistencePort = persistencePort;
		this.passwordEncoder = passwordEncoder;
		this.logger.setLogger(ClienteService.class);
	}
	
	@Override
	public ResponseEntity<ApiResponse<?>> registrarCliente(CrearCliente body) {
		logger.info(_CONST.ML_INI + "Registrando cliente"+ gson.toJson(body));
		Usuario usuario = new Usuario();
		usuario.setIdentificacion(body.getIdentificacion());
		usuario.setNombres(body.getNombres());
		usuario.setApellidos(body.getApellidos());
		usuario.setClave(passwordEncoder.encode(body.getClave()));
		usuario.setFechaNacimiento(body.getFechaNacimiento());
		usuario.setSexo(body.getSexo());
		usuario.setDireccion(body.getDireccion());
		usuario.setTelefono(body.getTelefono());
		usuario.setEstado(true);
		usuario.setFCreacion(new Date());
		usuario.setUCreacion(body.getIdentificacion());
		persistencePort.save(usuario);
		ApiResponse<ClienteResponse> successResponse = new ApiResponse<>(_CONST.MENSAJE_OK, null);
		logger.info(_CONST.ML_FIN + "Cliente Registrado" + gson.toJson(successResponse));
		return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
	}
	
	@Override
	public ResponseEntity<ApiResponse<?>> modificarCliente(ModificarCliente body) {
		logger.info(_CONST.ML_INI + "Modificando cliente"+ gson.toJson(body));
		Optional<Usuario> cliente = persistencePort.findById(body.getIdCliente());
		if(!cliente.isPresent()) {
		    ApiResponse<ClienteResponse> errorResponse = new ApiResponse<>("CLIENTE NO ENCONTRADO", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		Usuario entityUsuario = cliente.get();
		entityUsuario.setIdentificacion(body.getIdentificacion());
		entityUsuario.setNombres(body.getNombres());
		entityUsuario.setApellidos(body.getApellidos());
		entityUsuario.setClave(passwordEncoder.encode(body.getClave()));
		entityUsuario.setFechaNacimiento(body.getFechaNacimiento());
		entityUsuario.setSexo(body.getSexo());
		entityUsuario.setDireccion(body.getDireccion());
		entityUsuario.setTelefono(body.getTelefono());
		entityUsuario.setEstado(true);
		entityUsuario.setFCreacion(entityUsuario.getFCreacion());
		entityUsuario.setUCreacion(entityUsuario.getUCreacion());
		entityUsuario.setFActualizacion(new Date());
		entityUsuario.setUActualizacion(body.getIdentificacion());
		persistencePort.save(entityUsuario);
		ApiResponse<ClienteResponse> successResponse = new ApiResponse<>(_CONST.MENSAJE_OK, null);
		logger.info(_CONST.ML_FIN + "Cliente Modificado" + gson.toJson(successResponse));
		return ResponseEntity.ok(successResponse);
	}
	
	@Override
	public ResponseEntity<ApiResponse<ClienteResponse>> buscarClientePorIdentificacion(BuscarPorIdentificacion body) {
		logger.info(_CONST.ML_INI + gson.toJson(body));
		ClienteResponse clienteResponse = new ClienteResponse();
		Usuario cliente = persistencePort.findByIdentificacion(body.getIdentificacion());
		if(cliente==null) {
		    ApiResponse<ClienteResponse> errorResponse = new ApiResponse<>("CLIENTE NO ENCONTRADO", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		clienteResponse.setId(cliente.getId());
	    clienteResponse.setIdentificacion(cliente.getIdentificacion());
	    clienteResponse.setNombres(cliente.getNombres());
	    clienteResponse.setApellidos(cliente.getApellidos());
	    clienteResponse.setSexo(cliente.getSexo());
	    clienteResponse.setDireccion(cliente.getDireccion());
	    clienteResponse.setTelefono(cliente.getTelefono());
	    clienteResponse.setEdad(Util.calcularEdad(cliente.getFechaNacimiento()));
	    ApiResponse<ClienteResponse> successResponse = new ApiResponse<>(_CONST.MENSAJE_OK, clienteResponse);
		logger.info(_CONST.ML_FIN + gson.toJson(clienteResponse));
		return ResponseEntity.ok(successResponse);
	}
	
}

