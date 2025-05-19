package com.challenge.transactionalapi.config.enums;

import lombok.Getter;

@Getter
public enum TipoCuenta {
	
	CTACORRIENTE(1, "CTA. CORRIENTE"),
	CTAAHORROS(2, "CTA. AHORROS");

	private int id;
	private String descripcion;

	TipoCuenta(int id, String descripcion){
		this.id = id;
		this.descripcion = descripcion;
	}

}
