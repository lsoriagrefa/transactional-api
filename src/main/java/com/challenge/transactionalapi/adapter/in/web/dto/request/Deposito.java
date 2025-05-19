package com.challenge.transactionalapi.adapter.in.web.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deposito extends BuscarPorIdentificacion{
	
	@NotBlank(message = "EL NUMERO DE CUENTA ES OBLIGATORIO")
    private String numeroCuenta;
    
    @NotNull(message = "EL VALOR A DAPOSITAR ES OBLIGATORIO")
    @DecimalMin(value = "0.01", inclusive = true, message = "EL VALOR A DEPOSITAR DEBE SER POSITIVO")
    @Digits(integer = 12, fraction = 2, message = "SOLO SE PERMITEN HASTA DOS DECIMALES")
    private BigDecimal valor; 
}
