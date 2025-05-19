package com.challenge.transactionalapi.config.enums;

import lombok.Getter;

@Getter
public enum TipoMovimiento {
	DEPOSITO(1, "DEPÓSITO"),
	RETIRO(2, "RETIRO");

	private Integer id;
	private String descripcion;

	TipoMovimiento(Integer id, String descripcion){
		this.id = id;
		this.descripcion = descripcion;
	}
}
