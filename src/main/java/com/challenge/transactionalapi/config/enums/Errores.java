package com.challenge.transactionalapi.config.enums;

import com.challenge.transactionalapi.config._CONST;
import lombok.Getter;

@Getter
public enum Errores {
	GENERAL(_CONST.MENSAJE_ERROR),
    PATH_NOTFOUND("PATH NO ENCONTRADO");

    private final String msg;

    Errores(String msg) {
        this.msg = msg;
    }
}
