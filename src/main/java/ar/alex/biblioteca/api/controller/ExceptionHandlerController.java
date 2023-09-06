package ar.alex.biblioteca.api.controller;


import ar.alex.biblioteca.api.dto.ErrorDto;
import ar.alex.biblioteca.business.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(LibroNoPresenteException.class)
    public ResponseEntity<ErrorDto> handleLibroNoPresenteException (LibroNoPresenteException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LibroSinEjemplaresException.class)
    public ResponseEntity<ErrorDto> handleLibroSinEjemplaresException  (LibroSinEjemplaresException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstudianteNoPresenteException.class)
    public ResponseEntity<ErrorDto> handleEstudianteNoPresenteException  (EstudianteNoPresenteException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrestamoDuplicadoException.class)
    public ResponseEntity<ErrorDto> handlePrestamoDuplicadoException  (PrestamoDuplicadoException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(PrestamoSuperaRenovacionesException.class)
    public ResponseEntity<ErrorDto> handlePrestamoSuperaRenovacionesException  (PrestamoSuperaRenovacionesException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(PrestamoVencidoException.class)
    public ResponseEntity<ErrorDto> handlePrestamoVencidoException (PrestamoVencidoException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.GONE);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException  (RuntimeException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException  (Exception exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
