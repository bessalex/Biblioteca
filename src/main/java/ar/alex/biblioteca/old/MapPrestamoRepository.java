package ar.alex.biblioteca.old;
/*/
import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.business.model.PrestamoBO;

import java.util.*;

public class MapPrestamoRepository {


    private final Map<Integer, PrestamoBO> prestamoMap = new HashMap<>();

    public void save(PrestamoBO prestamo) {

        this.prestamoMap.put(prestamo.hashCode(),prestamo);
    }

    public List<PrestamoBO> findAll() {
        return new ArrayList<>(this.prestamoMap.values());
    }


    public Optional<PrestamoBO> findByLibroAndEstudiante(LibroBO libro, Estudiante estudiante) {

        return Optional.ofNullable(this.prestamoMap.get(new PrestamoBO(libro, estudiante).hashCode()));

    }

    public void update(PrestamoBO prestamo) {  // verifiar que libro exista es útil

        this.prestamoMap.put(prestamo.hashCode(), prestamo);
    }
}
/*/