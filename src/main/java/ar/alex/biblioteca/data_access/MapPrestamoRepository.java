package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.model.Libro;
import ar.alex.biblioteca.business.model.Prestamo;

import java.util.*;

public class MapPrestamoRepository {


    private final Map<Integer, Prestamo> prestamoMap = new HashMap<>();

    public void save(Prestamo prestamo) {

        this.prestamoMap.put(prestamo.hashCode(),prestamo);
    }

    public List<Prestamo> findAll() {
        return new ArrayList<>(this.prestamoMap.values());
    }


    public Optional<Prestamo> findByLibroAndEstudiante(Libro libro, Estudiante estudiante) {

        return Optional.ofNullable(this.prestamoMap.get(new Prestamo(libro, estudiante).hashCode()));

    }

    public void update(Prestamo prestamo) {  // verifiar que libro exista es útil

        this.prestamoMap.put(prestamo.hashCode(), prestamo);
    }
}
