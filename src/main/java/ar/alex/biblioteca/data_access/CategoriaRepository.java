package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {

    @Query(value = "SELECT * FROM libros where nombre = :#{1}")
    Optional<Categoria> findByNombre(String nombre);
}
