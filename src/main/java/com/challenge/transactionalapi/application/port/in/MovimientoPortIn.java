package com.challenge.transactionalapi.application.port.in;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Deposito;
import com.challenge.transactionalapi.adapter.in.web.dto.request.ListarMovimientos;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Retiro;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.MovimientoResponse;

public interface MovimientoPortIn {
	
	 ResponseEntity<ApiResponse<?>> retiro(Retiro body);
	 
	 ResponseEntity<ApiResponse<?>> deposito(Deposito body);
		
	 ResponseEntity<ApiResponse<List<MovimientoResponse>>> listarMovimientosPorFechaYUsuario(ListarMovimientos movimientos);
}
