package br.com.dcm.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by dmartinez on 10/05/2017.
 */
public class InternalServerException extends RuntimeException {
    public InternalServerException() {
        super("Erro interno no servidor");
    }
}
