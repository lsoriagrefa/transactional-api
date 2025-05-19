package com.challenge.transactionalapi.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcepcionGenerica extends RuntimeException {
	
    public ExcepcionGenerica(String mensajeError) {
        super(mensajeError);
    }

    public ExcepcionGenerica(String mensajeError, Throwable e) {
        super(mensajeError, e);
    }

}
