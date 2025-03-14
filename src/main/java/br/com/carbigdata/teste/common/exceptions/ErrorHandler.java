package br.com.carbigdata.teste.common.exceptions;

import br.com.carbigdata.teste.common.exceptions.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({Error.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDTO handle(Error exception){
        try {
            return new ErrorDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());

        }catch (Exception e){
            return new ErrorDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
}
