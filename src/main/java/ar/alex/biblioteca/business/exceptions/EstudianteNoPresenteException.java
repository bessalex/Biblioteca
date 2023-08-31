package ar.alex.biblioteca.business.exceptions;

public class EstudianteNoPresenteException extends BusinessException{

    public EstudianteNoPresenteException(int code, String message) {
        super(code, message);
    }
}
