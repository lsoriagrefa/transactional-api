package com.challenge.transactionalapi.application.port.in;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.challenge.transactionalapi.adapter.in.web.dto.request.BuscarPorIdentificacion;
import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCuenta;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.CuentaResponse;

public interface CuentaPorIn {
	
	 ResponseEntity<ApiResponse<?>> registrarCuenta(CrearCuenta body);
	
	 ResponseEntity<ApiResponse<List<CuentaResponse>>> buscarCuentasPorCliente(BuscarPorIdentificacion body);
}
