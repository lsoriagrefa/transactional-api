package com.challenge.transactionalapi.application.port.in;

import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCliente;
import com.challenge.transactionalapi.adapter.in.web.dto.request.ModificarCliente;
import org.springframework.http.ResponseEntity;
import com.challenge.transactionalapi.adapter.in.web.dto.request.BuscarPorIdentificacion;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ClienteResponse;

public interface ClientePortIn {
	
	ResponseEntity<ApiResponse<?>> registrarCliente(CrearCliente request);
	 
	ResponseEntity<ApiResponse<?>> modificarCliente(ModificarCliente body);
	
	ResponseEntity<ApiResponse<ClienteResponse>> buscarClientePorIdentificacion(BuscarPorIdentificacion busqueda);
	
}
