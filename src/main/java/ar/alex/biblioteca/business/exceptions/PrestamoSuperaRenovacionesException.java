package ar.alex.biblioteca.business.exceptions;

public class PrestamoSuperaRenovacionesException extends Exception{
    // para que sea checked
    public PrestamoSuperaRenovacionesException(String message) {
        super(message);
    }
}
