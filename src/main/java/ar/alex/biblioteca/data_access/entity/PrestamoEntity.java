package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

// https://www.baeldung.com/jpa-hibernate-associations

@Getter
@Entity
@Builder
@Table(name="prestamos")
@AllArgsConstructor
public class PrestamoEntity {
    @Id
    @NonNull
    private String id;
    @NonNull
    private final String isbnLibro;
    @NonNull
    private final Integer dniEstudiante;
    @NonNull
    private final LocalDate fechaInicio;
    @Setter
    private LocalDate fechaVencimiento ;
    @Setter
    private int nroRenovacion;
}
