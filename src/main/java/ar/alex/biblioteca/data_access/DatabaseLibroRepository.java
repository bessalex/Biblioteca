package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatabaseLibroRepository  extends JpaRepository<LibroEntity, String>  {

    @Query(value = "insert into libros ( isbn, autor, titulo,  categoria, ejemplares_disponibles) VALUES " +
            "(:#{#libro.isbn}, :#{#libro.autor}, :#{#libro.titulo}, " +
            ":#{#libro.categoria}, :#{#libro.ejemplares_disponibles})", nativeQuery = true)
    @NotNull
    LibroEntity save(@NotNull LibroEntity libro);

   // @Query("SELECT * FROM libros;")
    @NotNull
    List<LibroEntity> findAll();

    @Query(value = "SELECT * FROM libros where categoria = :#{#categoria.name}", nativeQuery = true)
    List<LibroEntity> findByCategoria(Categoria categoria);

    @Query(value = "SELECT * FROM libros where isbn = :#{#isbn}", nativeQuery = true)
    Optional<LibroEntity> findByIsbn(String isbn);


    @Query(value = "update libros " +
            "set autor = :#{#libro.autor}," +
            "   titulo = :#{#libro.titulo}," +
            "   categoria = :#{#libro.categoria}," +
            "   ejemplares_disponibles = :#{#libro.ejemplares_disponibles}" +
            " Where isbn = :#{#libro.isbn} )", nativeQuery = true)
    void update(LibroEntity libro);
}