package com.challenge.transactionalapi.application.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Deposito;
import com.challenge.transactionalapi.adapter.in.web.dto.request.ListarMovimientos;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Retiro;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.MovimientoResponse;
import com.challenge.transactionalapi.application.port.in.MovimientoPortIn;
import com.challenge.transactionalapi.application.port.out.ClientePersistencePort;
import com.challenge.transactionalapi.application.port.out.CuentaPersistencePort;
import com.challenge.transactionalapi.application.port.out.MovimientoPersistencePort;
import com.challenge.transactionalapi.common.UseCase;
import com.challenge.transactionalapi.config._CONST;
import com.challenge.transactionalapi.config.enums.TipoCuenta;
import com.challenge.transactionalapi.config.enums.TipoMovimiento;
import com.challenge.transactionalapi.config.logger.Logger;
import com.challenge.transactionalapi.domain.entity.Cuenta;
import com.challenge.transactionalapi.domain.entity.Movimiento;
import com.challenge.transactionalapi.domain.entity.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@UseCase
public class MovimientoService implements MovimientoPortIn {
	
	private final ClientePersistencePort clientePersistencePort;
	private final CuentaPersistencePort cuentaPersistencePort;
	private final MovimientoPersistencePort movimientoPersistencePort;
	private final Logger logger = new Logger();
	private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	public MovimientoService(CuentaPersistencePort cuentaPersistencePort, MovimientoPersistencePort movimientoPersistencePort,
			ClientePersistencePort clientePersistencePort) {
		this.movimientoPersistencePort = movimientoPersistencePort;
		this.cuentaPersistencePort = cuentaPersistencePort;
		this.clientePersistencePort = clientePersistencePort;
		this.logger.setLogger(MovimientoService.class);
	}
	
	@Override
	public ResponseEntity<ApiResponse<?>> retiro(Retiro body) {
		logger.info(_CONST.ML_INI + "retiro"+ gson.toJson(body));
		Optional<Cuenta> cuenta = cuentaPersistencePort.findCuentaByNumeroCuenta(body.getNumeroCuenta());
		ApiResponse<MovimientoResponse> response = new ApiResponse<>(_CONST.MENSAJE_OK);
		if(!cuenta.isPresent()) {
			response = new ApiResponse<>("NUMERO DE CUENTA NO EXISTE", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		BigDecimal valorRetiro = body.getValor().abs(); 
		BigDecimal saldoDisponible = cuenta.get().getSaldo();
		if(!tieneSaldoDisponible(saldoDisponible, valorRetiro)) {
		    response = new ApiResponse<>("SALDO INSUFICIENTE PARA REALIZAR EL RETIRO", null);
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		Cuenta cuentaEntity = cuenta.get();
	    BigDecimal nuevoSaldo = saldoDisponible.subtract(valorRetiro);
	    cuentaEntity.setSaldo(nuevoSaldo);
	    cuentaPersistencePort.save(cuentaEntity);
		Movimiento movimiento = new Movimiento();
		movimiento.setIdCuenta(cuenta.get().getId());
		movimiento.setIdTipoMovimiento(TipoMovimiento.RETIRO.getId());
		movimiento.setFecha(new Date());
		movimiento.setValor(body.getValor());
		movimiento.setSaldo(nuevoSaldo);
		movimiento.setFCreacion(new Date());
		movimiento.setUCreacion(body.getIdentificacion());
		movimientoPersistencePort.save(movimiento);
		response = new ApiResponse<>(_CONST.MENSAJE_OK, null);
		logger.info(_CONST.ML_FIN + "retiro" + gson.toJson(response));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ApiResponse<?>> deposito(Deposito body) {
		logger.info(_CONST.ML_INI + "deposito"+ gson.toJson(body));
		Optional<Cuenta> cuenta = cuentaPersistencePort.findCuentaByNumeroCuenta(body.getNumeroCuenta());
		ApiResponse<MovimientoResponse> response = new ApiResponse<>(_CONST.MENSAJE_OK);
		if(!cuenta.isPresent()) {
		    response = new ApiResponse<>("NUMERO DE CUENTA NO EXISTE", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		BigDecimal saldoDisponible = cuenta.get().getSaldo();
		Cuenta cuentaEntity = cuenta.get();
	    BigDecimal nuevoSaldo = saldoDisponible.add(body.getValor());
	    cuentaEntity.setSaldo(nuevoSaldo);
	    cuentaPersistencePort.save(cuentaEntity);
		Movimiento movimiento = new Movimiento();
		movimiento.setIdCuenta(cuenta.get().getId());
		movimiento.setIdTipoMovimiento(TipoMovimiento.DEPOSITO.getId());
		movimiento.setFecha(new Date());
		movimiento.setValor(body.getValor());
		movimiento.setSaldo(nuevoSaldo);
		movimiento.setFCreacion(new Date());
		movimiento.setUCreacion(body.getIdentificacion());
		movimientoPersistencePort.save(movimiento);
		response = new ApiResponse<>(_CONST.MENSAJE_OK, null);
		logger.info(_CONST.ML_FIN + "retiro" + gson.toJson(response));
		return ResponseEntity.ok(response);
	}

	public Boolean tieneSaldoDisponible(BigDecimal saldo, BigDecimal valorRetiro) {
	    return valorRetiro.compareTo(saldo) <= 0;
	}

	@Override
	public ResponseEntity<ApiResponse<List<MovimientoResponse>>> listarMovimientosPorFechaYUsuario(
			ListarMovimientos body) {
		logger.info(_CONST.ML_INI + "listarMovimientosPorFechaYUsuario"+ gson.toJson(body));
		Optional<Usuario> cliente = clientePersistencePort.findById(body.getIdCliente());
		ApiResponse<List<MovimientoResponse>> response = new ApiResponse<>(_CONST.MENSAJE_OK);
		if(!cliente.isPresent()) {
		    response = new ApiResponse<>("CLIENTE NO EXISTE", null);
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		Date fechaInicio = body.getFechaInicio();
		Date fechaFin = body.getFechaFin();
		String nombreCliente = cliente.get().getNombres() + " "+cliente.get().getApellidos();
		List<Movimiento> movimientos = movimientoPersistencePort.buscarPorFechaYUsuario(horaInicio(fechaInicio), horahorafin(fechaFin), body.getIdCliente());
		response = new ApiResponse<>(_CONST.MENSAJE_OK, mapearMovimientosToResponse(movimientos, nombreCliente));
		logger.info(_CONST.ML_FIN + "buscarCuentasPorCliente");
		return ResponseEntity.ok(response);
	}
	
	public List<MovimientoResponse> mapearMovimientosToResponse(List<Movimiento> movimientos, String nombreCliente) {
	    return movimientos.stream()
	            .map(movimiento -> mapearMovimientoToResponse(movimiento, nombreCliente))
	            .collect(Collectors.toList());
	}
	
	public MovimientoResponse mapearMovimientoToResponse(Movimiento movimiento, String nombreCliente) {
		MovimientoResponse response = new MovimientoResponse();
		Optional<Cuenta> cuenta = obtenerNumeroCuenta(movimiento.getIdCuenta());
		response.setCliente(nombreCliente);
	    response.setFecha(movimiento.getFecha());
		if(cuenta.isPresent()) {
			response.setNumeroCuenta(cuenta.get().getNumeroCuenta());
			response.setTipo(cuenta.get().getIdTipoCuenta().equals(TipoCuenta.CTAAHORROS.getId()) ? TipoCuenta.CTAAHORROS.getDescripcion():
				 TipoCuenta.CTACORRIENTE.getDescripcion());
		}
	    response.setSaldoInicial(obtenerSaldoInicial(movimiento.getValor(), movimiento.getSaldo(), movimiento.getIdTipoMovimiento()));
	    response.setMovimiento(movimiento.getValor());
	    response.setSaldoDisponible(movimiento.getSaldo());
	    return response;
	}
	
	private BigDecimal obtenerSaldoInicial(BigDecimal valor, BigDecimal saldo, Integer idTipoMovimiento) {
	    if (TipoMovimiento.DEPOSITO.getId().equals(idTipoMovimiento)) {
	        return saldo.subtract(valor);
	    }
	    return saldo.add(valor.abs());
	}
	
	private Optional<Cuenta> obtenerNumeroCuenta(Integer idCuenta) {
		return cuentaPersistencePort.findCuentaById(idCuenta);
	}
	public Date horaInicio(Date fechaInicio) {
		return cambiarHora(fechaInicio, 0, 0, 0);
	}
	public Date horahorafin(Date fechafin) {
		return cambiarHora(fechafin, 24, 59, 59);
	}
	private Date cambiarHora(Date fechaOriginal, int hora, int minuto, int segundo) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fechaOriginal);
	    calendar.set(Calendar.HOUR_OF_DAY, hora);
	    calendar.set(Calendar.MINUTE, minuto);
	    calendar.set(Calendar.SECOND, segundo);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
}
