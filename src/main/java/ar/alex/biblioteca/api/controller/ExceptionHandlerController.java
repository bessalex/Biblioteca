package ar.alex.biblioteca.api.controller;


import ar.alex.biblioteca.api.dto.ErrorDto;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.business.exceptions.LibroSinEjemplaresException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(LibroNoPresenteException.class)
    public ResponseEntity<ErrorDto> handleLibroNoPresenteException (LibroNoPresenteException exception){
        ErrorDto errorDto = new ErrorDto(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LibroSinEjemplaresException.class)
    public ResponseEntity<ErrorDto> handleLibroSinEjemplaresException  (LibroSinEjemplaresException exception){
        ErrorDto errorDto = new ErrorDto(exception.getCode(),exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    /*
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException  (RuntimeException exception){
        ErrorDto errorDto = new ErrorDto(999, exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException  (Exception exception){
        ErrorDto errorDto = new ErrorDto(999, exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
    */
}
