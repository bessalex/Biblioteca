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
    private int id;

    @Column(name = "id_categoria")
    private int idCategoria;

    @Column(name = "maximo_dias_prestamo")
    private int numMaximoDiasPrestamo;

    @Column(name = "maximo_renovaciones")
    private int numMaximoRenovaciones;

}
