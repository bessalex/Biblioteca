package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MapLibroRepository implements LibroRepository {

    private final Map<String, Libro> libroMap = new HashMap<>();
    @Override
    public void save(Libro libro) {
        this.libroMap.put(libro.getIsbn(),libro);
    }

    @Override
    public List<LibroDto> findAll() {
        return new ArrayList<>(this.libroMap.values()
                .stream()
                .map(Libro::mapToDTO)
                .collect(Collectors.toList())
        );
    }

    @Override
    public List<Libro> findByCategoria(Categoria categoria) {
        return this.libroMap.values().stream().filter(libro -> libro.getCategoria().equals(categoria)).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        System.out.println("isbn findByIsbn = " + isbn);
        if (this.libroMap.get(isbn) == null)
            return Optional.empty();
        return Optional.of(new Libro(this.libroMap.get(isbn)));
    }

    @Override
    public void update(Libro libro) {
        this.libroMap.put(libro.getIsbn(), libro);
    }
}
