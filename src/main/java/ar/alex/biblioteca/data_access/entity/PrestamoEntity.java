package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "prestamos")
@AllArgsConstructor
@RequiredArgsConstructor
public class PrestamoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "isbn_libro")
    private final String isbnLibro;
    @Column(name = "dni_estudiante")
    private final Integer dniEstudiante;
    @Column(name = "id_condicion_prestamo")
    private int idCondicionPrestamo;
    @Column(name = "fecha_inicio")
    private final LocalDate fechaInicio;
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento ;
    @Column(name = "nro_renovacion")
    private int nroRenovacion;

}
