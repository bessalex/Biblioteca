package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.Prestamo;

import java.util.*;

public class MapPrestamoRepository implements PrestamoRepository{


    private final Map<Integer, Prestamo> prestamoMap = new HashMap<>();
    @Override
    public void save(Prestamo prestamo) {

        this.prestamoMap.put(prestamo.hashCode(),prestamo);
    }

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(this.prestamoMap.values());
    }


    @Override
    public Optional<Prestamo> findByLibroAndEstudiante(Libro libro, Estudiante estudiante) {

        return Optional.ofNullable(this.prestamoMap.get(new Prestamo(libro, estudiante).hashCode()));

    }

    @Override
    public void update(Prestamo prestamo) {  // verifiar que libro exista es útil

        this.prestamoMap.put(prestamo.hashCode(), prestamo);
    }
}
