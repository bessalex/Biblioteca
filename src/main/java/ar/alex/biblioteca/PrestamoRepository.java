package ar.alex.biblioteca;

import java.util.List;

public interface PrestamoRepository {

    void save(Prestamo prestamo);

    List<Prestamo> findAll();

    Prestamo findByIsbnAndDni(String isbn, Integer dni);

    void update(Prestamo prestamo);
}
