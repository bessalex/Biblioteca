package ar.alex.biblioteca.data_access.entity;


import ar.alex.biblioteca.data_access.CategoriaRepository;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "condiciones_prestamo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CondicionPrestamoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "condiciones_prestamo_seq_gen")
    @SequenceGenerator(name = "condiciones_prestamo_seq_gen", sequenceName = "condiciones_prestamo_id_seq",allocationSize=1)
    private Integer id;

   //  @Column(name = "id_categoria")
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Categoria categoria;
    //private Integer idCategoria;

    @Column(name = "maximo_dias_prestamo")
    private int numMaximoDiasPrestamo;

    @Column(name = "maximo_renovaciones")
    private int numMaximoRenovaciones;

}
