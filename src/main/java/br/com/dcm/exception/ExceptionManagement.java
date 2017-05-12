package br.com.dcm.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manipulador de exceções.
 *
 * @author dmartinez
 * @since 10/05/2017
 */
@RestControllerAdvice
public class ExceptionManagement {

    /**
     * Log da classe
     */
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(ExceptionManagement.class);
    }

    /**
     * Inteceptor para erros do tipo InternalServerError
     * @param e Throwable
     * @return String mensagem
     */
    @ExceptionHandler(value={InternalServerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handler1(Exception e){
        return e.getMessage();
    }


    /**
     * Inteceptor para erros do tipo InternalServerError
     * @param e Throwable
     */
    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handler2(Exception e){}


}
