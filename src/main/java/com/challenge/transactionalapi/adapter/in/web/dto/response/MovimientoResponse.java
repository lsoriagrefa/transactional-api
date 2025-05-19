package com.challenge.transactionalapi.adapter.in.web.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoResponse {
    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
