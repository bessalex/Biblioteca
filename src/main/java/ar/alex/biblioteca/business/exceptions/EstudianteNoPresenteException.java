package ar.alex.biblioteca.business.exceptions;

public class EstudianteNoPresenteException extends Exception{
    // para que sea checked
    public EstudianteNoPresenteException(String message) {
        super(message);
    }
}
