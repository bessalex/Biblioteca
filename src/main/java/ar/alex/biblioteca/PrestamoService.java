package ar.alex.biblioteca;

import java.util.List;

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

    public Prestamo findByIsbnAndDni(String isbn, Integer dni) {
        return this.prestamoRepository.findByIsbnAndDni(isbn, dni);
    }

    public void update(Prestamo prestamo) {
        this.prestamoRepository.update(prestamo);
    }
}
