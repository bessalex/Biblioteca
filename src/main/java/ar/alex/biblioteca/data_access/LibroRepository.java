package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Categoria_abstract;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository  extends JpaRepository<LibroEntity, String>  {

    @Query(value = "SELECT * FROM libros where categoria = :#{#idCategoria}")
    List<LibroEntity> findByCategoria(Long idCategoria);

}
