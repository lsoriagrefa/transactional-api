package com.challenge.transactionalapi.adapter.in.web.dto.request;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ListarMovimientos {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message = "LA FECHA INICIO ES OBLIGATORIO")
	@Past(message = "LA FECHA INICIO DEBE SER ANTERIOR A LA FECHA ACTUAL")
	private Date fechaInicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message = "LA FECHA FIN ES OBLIGATORIO")
	@Past(message = "LA FECHA FIIN DEBE SER ANTERIOR A LA FECHA ACTUAL")
	private Date fechaFin;
	
	@NotNull(message = "EL ID DEL CLIENTE ES OBLIGATORIO")
	@Positive(message = "EL ID DEL CLIENTE DEBE SER UN NÚMERO POSITIVO")
	private Integer idCliente;
	
}
