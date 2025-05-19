package com.challenge.transactionalapi.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {
	
	private Integer id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String sexo;
    private String direccion;
    private String telefono;
    private Integer edad;
}
