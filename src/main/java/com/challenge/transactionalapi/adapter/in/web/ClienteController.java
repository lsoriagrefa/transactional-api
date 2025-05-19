package com.challenge.transactionalapi.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.transactionalapi.adapter.in.web.dto.request.BuscarPorIdentificacion;
import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCliente;
import com.challenge.transactionalapi.adapter.in.web.dto.request.ModificarCliente;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ClienteResponse;
import com.challenge.transactionalapi.application.port.in.ClientePortIn;
import com.challenge.transactionalapi.common.WebAdapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactional/api/v1/cliente")
public class ClienteController {
	
	private final ClientePortIn portIn;
	
    @PostMapping(path = "/registrarcliente")
    public ResponseEntity<ApiResponse<?>> registrarCliente(@Valid @RequestBody CrearCliente body) {
        return portIn.registrarCliente(body);
    }
    
    @PostMapping(path = "/modificarcliente")
    public ResponseEntity<ApiResponse<?>> modificarCliente(@Valid @RequestBody ModificarCliente body) {
        return portIn.modificarCliente(body);
    }
	
    @PostMapping(path = "/buscarclienteporid")
    public ResponseEntity<ApiResponse<ClienteResponse>> buscarClientePorIdentificacion(@Valid @RequestBody BuscarPorIdentificacion body) {
        return portIn.buscarClientePorIdentificacion(body);
    }

}
