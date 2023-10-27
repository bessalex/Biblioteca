package ar.alex.biblioteca.business.exceptions;

public class LibroNoPresenteException extends RuntimeException{

    public LibroNoPresenteException(String isbn) {
        super(String.format("Libro isbn: %s sin ejemplares disponibles", isbn));
    }
}
