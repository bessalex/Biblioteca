package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondicionPrestamoRepository extends JpaRepository<CondicionPrestamoEntity, Integer>  {

    @Query(value = "SELECT cp FROM CondicionPrestamoEntity cp where cp.idCategoria = ?1")
    Optional<CondicionPrestamoEntity> findByIdCategoria(Integer idCategoria);
}
