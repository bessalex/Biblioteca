package ar.alex.biblioteca.business.exceptions;

public class PrestamoNoPresenteException extends RuntimeException{
    // para que sea checked
    public PrestamoNoPresenteException(String id) {
        super(String.format("Prestamo: %s No existe en Biblioteca", id));
    }
}
