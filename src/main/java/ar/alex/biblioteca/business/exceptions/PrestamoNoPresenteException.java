package ar.alex.biblioteca.business.exceptions;

public class PrestamoNoPresenteException extends RuntimeException{
    // para que sea checked
    public PrestamoNoPresenteException(String message) {
        super(message);
    }
}
