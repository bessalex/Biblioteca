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

@Repository
public interface LibroRepository  extends JpaRepository<LibroEntity, String>  {

    @Query(value = "SELECT * FROM libros where categoria = :#{#categoria.name}")
    List<LibroEntity> findByCategoria(Categoria categoria);

}
