package ar.alex.biblioteca.business.exceptions;

public class EstudianteNoPresenteException extends RuntimeException{
    // para que sea checked
    public EstudianteNoPresenteException(String message) {
        super(message);
    }
}
