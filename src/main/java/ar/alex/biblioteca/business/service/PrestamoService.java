package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.Prestamo;
import ar.alex.biblioteca.data_access.PrestamoRepository;

import java.util.List;
import java.util.Optional;

public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public void save(Prestamo prestamo) {
        this.prestamoRepository.save(prestamo);
    }

    public List<Prestamo> findAll() {
        return this.prestamoRepository.findAll();
    }

    public Optional<Prestamo> findByLibroAndEstudiante(Libro libro, Estudiante estudiante) {
        if (libro == null){
            throw new RuntimeException("Libro no puede ser Nulo");
        }

        if (estudiante == null){
            throw new RuntimeException("Estudiante no puede ser Nulo");
        }

        return this.prestamoRepository.findByLibroAndEstudiante(libro, estudiante);
    }

    public void update(Prestamo prestamo) {
        this.prestamoRepository.update(prestamo);
    }
}
