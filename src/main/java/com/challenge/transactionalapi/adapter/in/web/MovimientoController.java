package com.challenge.transactionalapi.adapter.in.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Deposito;
import com.challenge.transactionalapi.adapter.in.web.dto.request.ListarMovimientos;
import com.challenge.transactionalapi.adapter.in.web.dto.request.Retiro;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.MovimientoResponse;
import com.challenge.transactionalapi.application.port.in.MovimientoPortIn;
import com.challenge.transactionalapi.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactional/api/v1/movimiento")
public class MovimientoController {
	
	private final MovimientoPortIn portIn;
	
    @PostMapping(path = "/deposito")
    public ResponseEntity<ApiResponse<?>> realizarDeposito(@Valid @RequestBody Deposito body) {
        return portIn.deposito(body);
    }
	
    @PostMapping(path = "/retiro")
    public ResponseEntity<ApiResponse<?>> realizarRetiro(@Valid @RequestBody  Retiro body) {
        return portIn.retiro(body);
    }
    
    @PostMapping(path = "/consutarmovimientos")
    public ResponseEntity<ApiResponse<List<MovimientoResponse>>> consultarMovimientosPorFechaYUsuario(@Valid @RequestBody  ListarMovimientos body) {
        return portIn.listarMovimientosPorFechaYUsuario(body);
    }
    
}
