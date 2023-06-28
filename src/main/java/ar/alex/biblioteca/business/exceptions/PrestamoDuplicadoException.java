package ar.alex.biblioteca.business.exceptions;

public class PrestamoDuplicadoException extends Exception{
    // para que sea checked
    public PrestamoDuplicadoException(String message) {
        super(message);
    }
}
