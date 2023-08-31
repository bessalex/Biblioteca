package ar.alex.biblioteca.business.exceptions;

public class LibroNoPresenteException extends BusinessException{

    public LibroNoPresenteException(int code, String message) {
        super(code, message);
    }
}
