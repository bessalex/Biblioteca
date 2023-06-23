package ar.alex;

import ar.alex.biblioteca.Categoria;
import ar.alex.biblioteca.Libro;
import ar.alex.biblioteca.LibroRepository;

import java.util.List;

public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void save(Libro libro) {
        this.libroRepository.save(libro);
    }

    public List<Libro> findAll() {
        return this.libroRepository.findAll();
    }

    public List<Libro> findByCategoria(Categoria categoria) {
        return this.libroRepository.findByCategoria(categoria);
    }

    public Libro findByIsbn(String isbn) {
        return this.libroRepository.findByIsbn(isbn);
    }

    public void update(Libro libroPrestar) {
        this.libroRepository.update(libroPrestar);
    }
}
