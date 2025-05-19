package com.challenge.transactionalapi.adapter.in.web.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaResponse {
	private Integer id;
	private Integer idCliente;
	private String numeroCuenta;
	private Integer idTipoCuenta;
	private String tipoCuenta;
	private BigDecimal saldo;
}
