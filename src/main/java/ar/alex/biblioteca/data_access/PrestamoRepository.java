package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.data_access.entity.PrestamoEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, String> {


   //buscar directamente por nombre del campo en la entity

    @Query(value = "select * " +
            " from  prestamos" +
            " Where isbn_libro = ?1 " +
            "   and dni_estudiante = ?2")
    Optional<PrestamoEntity> findByLibroAndEstudiante(String isbnLibro, Integer dniEstudiante);

    @Query(value = "update prestamos " +
            "set isbn_libro = :#{#prestamoEntity.isbnLibro}," +
            "   dni_estudiante = :#{#prestamoEntity.dniEstudiante}," +
            "   fecha_inicio = :#{#prestamoEntity.fechaInicio}," +
            "   fecha_vencimiento = :#{#prestamoEntity.fechaVencimiento}," +
            "   nro_renovacion = :#{#prestamoEntity.nroRenovacion}" +
            " Where id = :#{#prestamoEntity.id}")
    @Modifying
    void update(PrestamoEntity prestamoEntity);
}

