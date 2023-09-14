package ar.alex.biblioteca.api.controller;


import ar.alex.biblioteca.api.dto.ErrorDto;
import ar.alex.biblioteca.business.exceptions.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(LibroNoPresenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleLibroNoPresenteException (LibroNoPresenteException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LibroSinEjemplaresException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleLibroSinEjemplaresException  (LibroSinEjemplaresException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstudianteNoPresenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleEstudianteNoPresenteException  (@NotNull EstudianteNoPresenteException exception){
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrestamoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDto> handlePrestamoDuplicadoException  (PrestamoDuplicadoException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PrestamoSuperaRenovacionesException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorDto> handlePrestamoSuperaRenovacionesException  (PrestamoSuperaRenovacionesException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(PrestamoVencidoException.class)
    @ResponseStatus(HttpStatus.GONE)
    public ResponseEntity<ErrorDto> handlePrestamoVencidoException (PrestamoVencidoException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.GONE);
    }

    @ExceptionHandler(PrestamoNoPresenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handlePrestamoNoPresenteException (PrestamoNoPresenteException exception){
        ErrorDto errorDto = new ErrorDto(exception.toString());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException  (RuntimeException exception){
        ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException  (Exception exception){
        ErrorDto errorDto = new ErrorDto(exception.toString());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
