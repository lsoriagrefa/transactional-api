package com.challenge.transactionalapi.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ModificarCliente extends CrearCliente {
	
	@NotNull(message = "EL ID DEL CLIENTE ES OBLIGATORIO")
	@Positive(message = "EL ID DEL CLIENTE DEBE SER UN NÚMERO POSITIVO")
    private Integer idCliente;
}
