package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@Builder
public class PrestamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "isbn_libro")
    private final String isbnLibro;
    @Column(name = "dni_estudiante")
    private final Integer dniEstudiante;
    @Column(name = "id_condicion_prestamo")
    private Long idCondicionPrestamo;
    @Column(name = "fecha_inicio")
    private final LocalDate fechaInicio;
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento ;
    @Column(name = "nro_renovacion")
    private int nroRenovacion;

}
