package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.data_access.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>  {


    List<Categoria> findByNombre(String nombre);
}
