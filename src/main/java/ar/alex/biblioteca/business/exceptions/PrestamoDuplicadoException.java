package ar.alex.biblioteca.business.exceptions;

public class PrestamoDuplicadoException extends RuntimeException{
    // para que sea checked
    public PrestamoDuplicadoException(String message) {
        super(message);
    }
}
