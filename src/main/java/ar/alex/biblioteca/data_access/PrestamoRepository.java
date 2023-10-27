package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.Prestamo;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import ar.alex.biblioteca.data_access.entity.PrestamoEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, String> {


    @Query(value = "select * " +
            " from  prestamos" +
            " Where isbn_libro = :#{#isbnLibro} " +
            "   and dni_estudiante = :#{#dniEstudiante},", nativeQuery = true)
    @NotNull
    Optional<PrestamoEntity> findByLibroAndEstudiante(String isbnLibro, Integer dniEstudiante);

    @Query(value = "update prestamos " +
            "set isbn_libro = :#{#prestamoEntity.isbnLibro}," +
            "   dni_estudiante = :#{#prestamoEntity.dniEstudiante}," +
            "   fecha_inicio = :#{#prestamoEntity.fechaInicio}," +
            "   fecha_vencimiento = :#{#prestamoEntity.fechaVencimiento}," +
            "   nro_renovacion = :#{#prestamoEntity.nroRenovacion}" +
            " Where id = :#{#prestamoEntity.id}", nativeQuery = true)
    void update(PrestamoEntity prestamoEntity);
}

