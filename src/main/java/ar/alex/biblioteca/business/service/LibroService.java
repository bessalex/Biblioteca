package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.exceptions.CategoriaNoPresenteException;
import ar.alex.biblioteca.business.mapper.LibroBOMapper;
import ar.alex.biblioteca.business.model.Categoria;
import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;

import ar.alex.biblioteca.data_access.CategoriaRepository;
import ar.alex.biblioteca.data_access.LibroRepository;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LibroService {

    @NonNull
    private final LibroRepository libroRepository;
    @NonNull
    private final CategoriaRepository categoriaRepository;
    @NonNull
    private final LibroBOMapper libroMapper;


    public void save(LibroBO libro) {


        libroRepository.save(libroMapper.mapToLibroEntity(libro));
    }

    public List<LibroBO> findAll() {
        return this.libroMapper.mapToLibroBOList(this.libroRepository.findAll());
    }

    public List<LibroBO> findByCategoria(String categoria)  {
        if (categoria == null)
            return this.findAll();

        Categoria categoriaEntity = this.categoriaRepository.findByNombre(categoria)
                .orElseThrow(() -> new CategoriaNoPresenteException(categoria));

        return this.libroMapper.mapToLibroBOList(
                this.libroRepository.findByCategoria(categoriaEntity.getId()));
    }

    public LibroBO findByIsbn(String isbn) {
        LibroEntity libroEntity = this.libroRepository.findById(isbn)
                .orElseThrow(() -> new LibroNoPresenteException(isbn));

        return this.libroMapper.mapToLibroBO(libroEntity);
    }

    public void update(LibroBO libro) {
        this.save(libro);
    }

}
