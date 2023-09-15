package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.data_access.LibroRepository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Libro> findByIsbn(String isbn) throws LibroNoPresenteException {
        return this.libroRepository.findByIsbn(isbn);
    }

    public void update(Libro libroPrestar) {
        this.libroRepository.update(libroPrestar);
    }

}
