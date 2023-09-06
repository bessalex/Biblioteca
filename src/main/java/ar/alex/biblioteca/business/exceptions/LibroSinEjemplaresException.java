package ar.alex.biblioteca.business.exceptions;

public class LibroSinEjemplaresException extends RuntimeException{

    public LibroSinEjemplaresException(String message) {
        super(message);
    }
}
