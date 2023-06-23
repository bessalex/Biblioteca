package ar.alex.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrestamoRepositoryImpl implements PrestamoRepository{


    private final Map<String,Prestamo> prestamoMap = new HashMap<>();
    @Override
    public void save(Prestamo prestamo) {
        String clavePrestamo = prestamo.getLibro().getIsbn() + "|" + prestamo.getEstudiante().getDni().toString();

        this.prestamoMap.put(clavePrestamo,prestamo);
    }

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(this.prestamoMap.values());
    }


    @Override
    public Prestamo findByIsbnAndDni(String isbn, Integer dni) {
        String clave = isbn + "|" + dni.toString();
        if (this.prestamoMap.get(clave) == null)
            return null;
        return new Prestamo (this.prestamoMap.get(clave));
    }

    @Override
    public void update(Prestamo prestamo) {  // verifiar que libro exista es útil
        String clave = prestamo.getLibro().getIsbn() + "|" + prestamo.getEstudiante().getDni().toString();

        this.prestamoMap.put(clave, prestamo);
    }
}
