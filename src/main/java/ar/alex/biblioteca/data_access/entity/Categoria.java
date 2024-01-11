package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


// @Builder  // NO VA
@RequiredArgsConstructor
@Table(name = "categorias")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Categoria {

    @Id
    @SequenceGenerator(name = "categorias_seq_gen", sequenceName = "categorias_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorias_seq_gen")
    private Integer id;
    @Column(name = "nombre")
    @NonNull private String nombre;

}
