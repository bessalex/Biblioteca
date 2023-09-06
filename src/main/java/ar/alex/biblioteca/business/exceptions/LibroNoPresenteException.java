package ar.alex.biblioteca.business.exceptions;

public class LibroNoPresenteException extends RuntimeException{

    public LibroNoPresenteException(String message) {
        super(message);
    }
}
