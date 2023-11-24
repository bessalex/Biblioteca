package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "condiciones_prestamo")
@Data
@Builder
public class CondicionPrestamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "maximo_dias_prestamo")
    private int numMaximoDiasPrestamo;

    @Column(name = "maximo_renovaciones")
    private int numMaximoRenovaciones;

}
