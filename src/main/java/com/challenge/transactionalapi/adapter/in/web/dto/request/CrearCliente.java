package com.challenge.transactionalapi.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CrearCliente extends Persona{
	
	@NotBlank(message = "LA CLAVE DEL CLIENTE ES OBLIGATORIO")
	private String clave;
}
