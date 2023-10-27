package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.data_access.DatabaseLibroRepository;
import ar.alex.biblioteca.data_access.LibroRepository;

import ar.alex.biblioteca.data_access.entity.LibroEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {

    @NonNull
    private DatabaseLibroRepository libroRepository;

    public void save(Libro libro) {
        libroRepository.save(libro.mapToEntity());
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
                .orElseGet(() -> {
                    throw new LibroNoPresenteException(isbn);
                }));
    }

    public void update(Libro libro) {
        this.libroRepository.update(libro.mapToEntity());
    }

}
