package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.data_access.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, String> {


   //buscar directamente por nombre del campo en la entity

    @Query(value = "select p " +
            " from  PrestamoEntity p" +
            " Where p.isbnLibro = ?1 " +
            "   and p.dniEstudiante = ?2")
    Optional<PrestamoEntity> findByLibroAndEstudiante(String isbnLibro, Integer dniEstudiante);

}

