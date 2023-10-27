package ar.alex.biblioteca.business.exceptions;

public class EstudianteNoPresenteException extends RuntimeException{

    public EstudianteNoPresenteException(Integer dni) {
        super(String.format("Estudiante DNI: %s No está presente", dni));
    }

}
