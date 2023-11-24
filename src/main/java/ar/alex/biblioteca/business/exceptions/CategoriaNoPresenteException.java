package ar.alex.biblioteca.business.exceptions;

public class CategoriaNoPresenteException extends RuntimeException{

    public CategoriaNoPresenteException(String nombre) {
        super(String.format("Cagegoria %s No existente en Base de Datos", nombre));
    }
}
