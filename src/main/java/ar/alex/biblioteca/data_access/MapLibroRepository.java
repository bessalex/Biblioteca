package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapLibroRepository implements LibroRepository {

    private final Map<String, Libro> libroMap = new HashMap<>();
    @Override
    public void save(Libro libro) {
        this.libroMap.put(libro.getIsbn(),libro);
    }

    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(this.libroMap.values());
    }

    @Override
    public List<Libro> findByCategoria(Categoria categoria) {
        return this.libroMap.values().stream().filter(libro -> libro.getCategoria() == categoria).
                collect(Collectors.toList());
    }

    @Override
    public Libro findByIsbn(String isbn) {
        if (this.libroMap.get(isbn) == null)
            return null;
        return new Libro (this.libroMap.get(isbn));
    }

    @Override
    public void update(Libro libro) {
        this.libroMap.put(libro.getIsbn(), libro);
    }
}
