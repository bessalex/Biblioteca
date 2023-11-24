package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.CategoriaBO;
import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.exceptions.CategoriaNoPresenteException;
import ar.alex.biblioteca.business.exceptions.EstudianteNoPresenteException;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.data_access.CategoriaRepository;
import ar.alex.biblioteca.data_access.EstudianteRepository;
import ar.alex.biblioteca.data_access.entity.CategoriaEntity;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoriaService {

    @NonNull
    private final CategoriaRepository categoriaRepository;

    public void save(String categoriaName) {
        this.categoriaRepository.save(CategoriaEntity.builder().nombre(categoriaName).build());
    }

    public CategoriaBO findByName(String nombre) {
        CategoriaEntity categoriaEntity = this.categoriaRepository.findByNombre(nombre)
                .orElseThrow( () -> new CategoriaNoPresenteException(nombre));
        return new CategoriaBO(categoriaEntity.getNombre());
    }

    public Libro findById(String nombre) {
        return new Libro(this.libroRepository.findById(isbn)
                .orElseThrow(() -> new LibroNoPresenteException(isbn)));
    }

}
