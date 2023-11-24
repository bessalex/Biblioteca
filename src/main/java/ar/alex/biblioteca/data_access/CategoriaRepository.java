package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.data_access.entity.CategoriaEntity;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>  {

    @Query(value = "SELECT * FROM libros where nombre = :#{1}")
    Optional<CategoriaEntity> findByNombre(String nombre);
}
