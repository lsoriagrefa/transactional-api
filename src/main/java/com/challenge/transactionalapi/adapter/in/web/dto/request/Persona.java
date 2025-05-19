package com.challenge.transactionalapi.adapter.in.web.dto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persona {
	
	@NotBlank(message = "LA IDENTIFICACIÓN DEL CLIENTE ES OBLIGATORIO")
	@Size(max = 15, message = "LA IDENTIFICACIÓN NO DEBE SUPERAR 15 CARACTERES")
    private String identificacion;
	
	@NotBlank(message = "LOS NOMBRES DEL CLIENTE ES OBLIGATORIO")
	@Size(max = 50, message = "LOS NOMBRES NO DEBEN SUPERAR 50 CARACTERES")
    private String nombres;
	
	@NotBlank(message = "LOS APELLIDOS DEL CLIENTE ES OBLIGATORIO")
	@Size(max = 50, message = "LOS APELLIDOS NO DEBEN SUPERAR 50 CARACTERES")
    private String apellidos;
	
	@NotBlank(message = "EL GÉNERO DEL CLIENTE ES OBLIGATORIO")
	@Pattern(regexp = "M|F", message = "EL GÉNERO DEBE SER 'M' O 'F'")
    private String sexo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message = "FECHA DE NACIMIENTO DEL CLIENETE ES OBLIGATORIO")
	@Past(message = "LA FECHA DE NACIMIENTO DEBE SER ANTERIOR A LA FECHA ACTUAL")
    private Date fechaNacimiento;
	
	@NotBlank(message = "LA DIRECCIÓN DEL CLIENTE ES OBLIGATORIO")
	private String direccion;
	
	@NotBlank(message = "LA TELÉFONO DEL CLIENTE ES OBLIGATORIO")
	@Size(max = 12, message = "EL TELÉFONO NO DEBE SUPERAR 12 CARACTERES")
    @Pattern(regexp = "^[0-9]+$", message = "EL TELÉFONO SOLO DEBE CONTENER NÚMEROS")
	private String telefono;
}
