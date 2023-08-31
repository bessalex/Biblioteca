package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {


    void save(Libro libro);

    List<LibroDto> findAll();

    List<Libro> findByCategoria(Categoria categoria);

    Optional<Libro> findByIsbn(String isbn);

    void update(Libro libroPrestar);
}
