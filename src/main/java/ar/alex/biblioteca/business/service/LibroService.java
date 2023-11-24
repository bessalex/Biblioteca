package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.model.Libro;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.data_access.DatabaseLibroRepository;

import ar.alex.biblioteca.data_access.entity.LibroEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroService {

    @NonNull
    private final DatabaseLibroRepository libroRepository;

    public void save(Libro libro) {
        libroRepository.save(LibroEntity.builder()
                .autor(libro.getAutor())
                .titulo(libro.getTitulo())
                .isbn(libro.getIsbn()).categoria(libro.getCategoria().toString())
                .ejemplares_disponibles(libro.getEjemplares_disponibles())
                .build());
    }

    public List<Libro> findAll() {
        return this.libroRepository.findAll()
                    .stream()
                    .map(Libro::new)
                .collect(Collectors.toList());
    }

    public List<Libro> findByCategoria(String categoria)  {
        if (categoria == null)
            return this.findAll();

        Categoria toFind = Categoria.create(categoria);
        return this.libroRepository.findByCategoria(toFind)
                .stream().map(Libro::new)
                .collect(Collectors.toList());
    }

    public Libro findByIsbn(String isbn) {
        return new Libro(this.libroRepository.findById(isbn)
                .orElseThrow(() -> new LibroNoPresenteException(isbn)));
    }

    public void update(Libro libro) {
        LibroEntity libroEntity = this.libroRepository.findById(libro.getIsbn())
                .orElseThrow(() -> new LibroNoPresenteException(libro.getIsbn()));

        this.libroRepository.save(libro.mapToEntity());
    }

}
