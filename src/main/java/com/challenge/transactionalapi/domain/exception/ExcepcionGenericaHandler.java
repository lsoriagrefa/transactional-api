package com.challenge.transactionalapi.domain.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.config.enums.Errores;
import com.challenge.transactionalapi.config.logger.Logger;

@ControllerAdvice
public class ExcepcionGenericaHandler {
	
	private final Logger logger = new Logger();

    @ExceptionHandler(value = {ExcepcionGenerica.class})
    public ResponseEntity<ApiResponse<?>> handlerExcepcionGenerica(ExcepcionGenerica excepcionGenerica, HandlerMethod handlerMethod) {
        return getError(excepcionGenerica.getMessage(), handlerMethod, excepcionGenerica, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponse<?>> handlerException(Exception exception, HandlerMethod handlerMethod) {
        return getError(Errores.GENERAL.getMsg(), handlerMethod, exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HandlerMethod handlerMethod) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Error de validación");
        return getError(errorMessage, handlerMethod, exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoResourceFoundException(NoResourceFoundException exception) {
        return getError(Errores.PATH_NOTFOUND.getMsg(), null, exception, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        String errorMessage = exception.getRootCause() != null
                ? exception.getRootCause().getMessage()
                : "Error de integridad de datos en la base de datos";
        return getError(errorMessage, null, exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiResponse<?>> getError(String msg, HandlerMethod handlerMethod, Throwable exception, HttpStatus status) {
        ApiResponse<?> respuesta = new ApiResponse<>(msg);
        logger.setLogger(handlerMethod == null ? ExcepcionGenericaHandler.class : handlerMethod.getBean().getClass());
        logger.error(exception.getMessage(), exception);
        return ResponseEntity.status(status).body(respuesta);
    }
}
