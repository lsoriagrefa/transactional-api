package com.challenge.transactionalapi.adapter.in.web.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CrearCuenta extends BuscarPorIdentificacion {
	
    @NotNull(message = "EL ID DEL CLIENTE DEL TIPO DE EVENTO ES OBLIGATORIO")
    private Integer idCliente;
    @NotNull(message = "EL ID DEL TIPO DE CUENTA ES OBLIGATORIO")
    private Integer idTipoCuenta;
    @NotNull(message = "EL SALDO INICIAL ES OBLIGATORIO (PUEDE SER CERO)")
	@DecimalMin(value = "0.0", inclusive = true, message = "EL SALDO NO PUEDE SER NEGATIVO")
    private BigDecimal saldo;
}
