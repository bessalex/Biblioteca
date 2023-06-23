package ar.alex.biblioteca;

import java.util.List;

public interface LibroRepository {


    void save(Libro libro);

    List<Libro> findAll();

    List<Libro> findByCategoria(Categoria categoria);

    Libro findByIsbn(String isbn);

    void update(Libro libroPrestar);
}
