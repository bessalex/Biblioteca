package ar.alex.biblioteca.business.exceptions;

public class LibroSinEjemplaresException extends BusinessException{

    public LibroSinEjemplaresException(int code, String message) {
        super(code, message);
    }
}
