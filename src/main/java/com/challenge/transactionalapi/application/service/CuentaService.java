package com.challenge.transactionalapi.application.service;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.challenge.transactionalapi.adapter.in.web.dto.request.BuscarPorIdentificacion;
import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCuenta;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.CuentaResponse;
import com.challenge.transactionalapi.application.port.in.CuentaPorIn;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.application.port.out.CuentaPersistencePort;
import com.challenge.transactionalapi.common.UseCase;
import com.challenge.transactionalapi.config._CONST;
import com.challenge.transactionalapi.config.enums.TipoCuenta;
import com.challenge.transactionalapi.config.logger.Logger;
import com.challenge.transactionalapi.domain.entity.Cuenta;
import com.challenge.transactionalapi.domain.entity.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UseCase
public class CuentaService implements CuentaPorIn{
	
	private final CuentaPersistencePort persistencePort;
	private final ClientePersistencePort clientePersistencePort;
	private final Logger logger = new Logger();
	private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	public CuentaService(CuentaPersistencePort persistencePort, PasswordEncoder passwordEncoder,
			ClientePersistencePort clientePersistencePort) {
		this.clientePersistencePort = clientePersistencePort;
		this.persistencePort = persistencePort;
		this.logger.setLogger(CuentaService.class);
	}
	
	@Override
	public ResponseEntity<ApiResponse<?>> registrarCuenta(CrearCuenta body) {
		logger.info(_CONST.ML_INI + "Registrando cuenta"+ gson.toJson(body));
		Optional<Usuario> usuario = clientePersistencePort.findById(body.getIdCliente());
		if(!usuario.isPresent()) {
		    ApiResponse<CuentaResponse> errorResponse = new ApiResponse<>("CLIENTE NO EXISTE", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		Cuenta cuenta = new Cuenta();
		cuenta.setIdUsuario(body.getIdCliente());
		cuenta.setIdTipoCuenta(body.getIdTipoCuenta());
		cuenta.setSaldo(body.getSaldo());
		cuenta.setEstado(true);
		cuenta.setFCreacion(new Date());
		cuenta.setUCreacion(body.getIdentificacion());
		persistencePort.save(cuenta);
		ApiResponse<CuentaResponse> successResponse = new ApiResponse<>(_CONST.MENSAJE_OK, null);
		logger.info(_CONST.ML_FIN + "registrarCuenta" + gson.toJson(successResponse));
		return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
	}

	@Override
	public ResponseEntity<ApiResponse<List<CuentaResponse>>> buscarCuentasPorCliente(BuscarPorIdentificacion body) {
		logger.info(_CONST.ML_INI + "buscarCuentasPorCliente"+ gson.toJson(body));
		Usuario usuario = clientePersistencePort.findByIdentificacion(body.getIdentificacion());
		if(usuario==null) {
		    ApiResponse<List<CuentaResponse>> errorResponse = new ApiResponse<>("CLIENTE NO ENCONTRADO", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		List<Cuenta> cuentas = persistencePort.findCuentasByIdUsuario(usuario.getId());
		ApiResponse<List<CuentaResponse>> successResponse = new ApiResponse<>(_CONST.MENSAJE_OK, mapearCuentasToResponse(cuentas));
		logger.info(_CONST.ML_FIN + "buscarCuentasPorCliente");
		return ResponseEntity.ok(successResponse);
	}
	
	public CuentaResponse mapearCuentaToResponse(Cuenta cuenta) {
	    CuentaResponse response = new CuentaResponse();
	    response.setId(cuenta.getId());
	    response.setNumeroCuenta(cuenta.getNumeroCuenta());
	    response.setIdTipoCuenta(cuenta.getIdTipoCuenta());
	    response.setTipoCuenta(obtenerDescripcionTipoCuenta(cuenta.getIdTipoCuenta()));
	    response.setSaldo(cuenta.getSaldo());
	    response.setIdCliente(cuenta.getIdUsuario());
	    return response;
	}
	
	public List<CuentaResponse> mapearCuentasToResponse(List<Cuenta> cuentas) {
	    return cuentas.stream()
	            .map(this::mapearCuentaToResponse)
	            .collect(Collectors.toList());
	}
	
	private String obtenerDescripcionTipoCuenta(Integer idTipoCuenta) {
		String tipoCuenta = idTipoCuenta == TipoCuenta.CTAAHORROS.getId() ? TipoCuenta.CTAAHORROS.getDescripcion() : 
		TipoCuenta.CTACORRIENTE.getDescripcion();
	    return tipoCuenta ;
	}

}
