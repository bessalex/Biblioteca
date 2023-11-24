package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondicionPrestamoRepository extends JpaRepository<CondicionPrestamoEntity, Long>  {

    @Query(value = "SELECT * FROM condiciones_prestamo where id_categoria = ?1")
    Optional<CondicionPrestamoEntity> findByIdCategoria(Long idCategoria);
}
