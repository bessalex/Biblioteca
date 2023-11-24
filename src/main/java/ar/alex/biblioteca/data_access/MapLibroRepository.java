package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.data_access.entity.LibroEntity;

import java.util.*;
import java.util.stream.Collectors;

public class MapLibroRepository {

    private final Map<String, LibroEntity> libroMap = new HashMap<>();

    public void save(LibroEntity libro) {
        this.libroMap.put(libro.getIsbn(),libro);
    }


    public List<LibroEntity> findAll() {
        return null;
    }



    public List<LibroEntity> findByCategoria(Categoria categoria) {
        return this.libroMap.values().stream()
                .filter(libro -> libro.getCategoria().equals(categoria)).
                collect(Collectors.toList());
    }


    public Optional<LibroEntity> findByIsbn(String isbn) {
        if (this.libroMap.get(isbn) == null)
            return Optional.empty();
        return Optional.of(this.libroMap.get(isbn));
    }


    public void update(LibroEntity libro) {
        this.libroMap.put(libro.getIsbn(), libro);
    }

}
