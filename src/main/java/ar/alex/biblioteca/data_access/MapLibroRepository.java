package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
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
