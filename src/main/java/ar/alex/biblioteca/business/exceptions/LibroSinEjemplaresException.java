package ar.alex.biblioteca.business.exceptions;

public class LibroSinEjemplaresException extends Exception{
    // para que sea checked
    public LibroSinEjemplaresException(String message) {
        super(message);
    }
}
