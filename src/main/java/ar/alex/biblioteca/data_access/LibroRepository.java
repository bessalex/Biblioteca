package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository  {


    LibroEntity save(LibroEntity libro);

    List<LibroEntity> findAll();

    List<LibroEntity> findByCategoria(Categoria categoria);

    Optional<LibroEntity> findByIsbn(String isbn);

    void update(LibroEntity libroPrestar);
}
