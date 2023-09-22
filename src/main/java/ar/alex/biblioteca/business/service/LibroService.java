package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.data_access.DatabaseLibroRepository;
import ar.alex.biblioteca.data_access.LibroRepository;

import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private DatabaseLibroRepository libroRepository;


    public LibroEntity save(Libro libro) {
        return libroRepository.save(libro.mapToEntity());
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

    public Optional<Libro> findByIsbn(String isbn) {
        Optional<LibroEntity> libroEntityOptional = this.libroRepository.findByIsbn(isbn);
        return libroEntityOptional.map(libroEntity -> new Libro(libroEntityOptional.get()));
    }

    public void update(Libro libro) {
        this.libroRepository.update(libro.mapToEntity());
    }

}
