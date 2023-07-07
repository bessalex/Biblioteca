package ar.alex.biblioteca.business.exceptions;

public class PrestamoVencidoException extends Exception{
    // para que sea checked
    public PrestamoVencidoException(String message) {
        super(message);
    }
}
