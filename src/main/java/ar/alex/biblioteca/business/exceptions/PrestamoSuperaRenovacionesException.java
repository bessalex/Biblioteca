package ar.alex.biblioteca.business.exceptions;

public class PrestamoSuperaRenovacionesException extends RuntimeException{
    // para que sea checked
    public PrestamoSuperaRenovacionesException(String message) {
        super(message);
    }
}
