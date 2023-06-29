package ar.alex.biblioteca.business.exceptions;

public class LibroNoPresenteException extends RuntimeException{
    // para que sea checked
    public LibroNoPresenteException(String message) {
        super(message);
    }
}
