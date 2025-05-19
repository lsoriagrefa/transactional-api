package com.challenge.transactionalapi.adapter.in.web.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Retiro extends BuscarPorIdentificacion{
	
	@NotBlank(message = "EL NUMERO DE CUENTA ES OBLIGATORIO")
    private String numeroCuenta;
    
    @NotNull(message = "EL VALOR A RETIRAR ES OBLIGATORIO")
    @DecimalMax(value = "0.0", inclusive = false, message = "EL VALOR DEL RETIRO DEBE SER NEGATIVO")
    @Digits(integer = 12, fraction = 2, message = "SOLO SE PERMITEN HASTA DOS DECIMALES")
    private BigDecimal valor; 
    
}
