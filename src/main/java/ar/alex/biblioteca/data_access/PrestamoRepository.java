package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {

    void save(Prestamo prestamo);

    List<Prestamo> findAll();

    Optional<Prestamo> findByLibroAndEstudiante(Libro libro, Estudiante estudiante);

    void update(Prestamo prestamo);
}
