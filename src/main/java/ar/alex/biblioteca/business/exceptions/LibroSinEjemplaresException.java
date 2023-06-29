package ar.alex.biblioteca.business.exceptions;

public class LibroSinEjemplaresException extends RuntimeException{
    // para que sea checked
    public LibroSinEjemplaresException(String message) {
        super(message);
    }
}
