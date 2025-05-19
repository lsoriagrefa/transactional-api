package com.challenge.transactionalapi.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BuscarPorIdentificacion {
	
	@NotBlank(message = "LA IDENTIFICACIÓN DEL USUARIO ES OBLIGATORIO")
    private String identificacion;
    
}
