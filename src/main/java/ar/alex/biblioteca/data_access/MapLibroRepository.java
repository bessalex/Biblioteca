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

public class MapLibroRepository implements LibroRepository {

    private final Map<String, LibroEntity> libroMap = new HashMap<>();
    @Override
    public LibroEntity save(LibroEntity libro) {
        this.libroMap.put(libro.getIsbn(),libro);
        return libro;
    }

    @Override
    public List<LibroEntity> findAll() {
        return null;
    }


    @Override
    public List<LibroEntity> findByCategoria(Categoria categoria) {
        return this.libroMap.values().stream()
                .filter(libro -> libro.getCategoria().equals(categoria)).
                collect(Collectors.toList());
    }

    @Override
    public Optional<LibroEntity> findByIsbn(String isbn) {
        if (this.libroMap.get(isbn) == null)
            return Optional.empty();
        return Optional.of(this.libroMap.get(isbn));
    }

    @Override
    public void update(LibroEntity libro) {
        this.libroMap.put(libro.getIsbn(), libro);
    }

}
