package ar.alex.biblioteca.business.exceptions;

public class LibroNoPresenteException extends Exception{
    // para que sea checked
    public LibroNoPresenteException(String message) {
        super(message);
    }
}
