package com.challenge.transactionalapi.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.transactionalapi.adapter.in.web.dto.request.BuscarPorIdentificacion;
import com.challenge.transactionalapi.adapter.in.web.dto.request.CrearCuenta;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.CuentaResponse;
import com.challenge.transactionalapi.application.port.in.CuentaPorIn;
import com.challenge.transactionalapi.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactional/api/v1/cuenta")
public class CuentaController {
	
	private final CuentaPorIn portIn;
	
    @PostMapping(path = "/registrarcuenta")
    public ResponseEntity<ApiResponse<?>> registrarCuenta(@Valid @RequestBody CrearCuenta body) {
        return portIn.registrarCuenta(body);
    }
	
    @PostMapping(path = "/buscarcuentasporcliente")
    public ResponseEntity<ApiResponse<List<CuentaResponse>>> buscarCuentasPorCliente(@Valid @RequestBody  BuscarPorIdentificacion body) {
        return portIn.buscarCuentasPorCliente(body);
    }
}
