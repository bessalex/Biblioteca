package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


// @Builder  // NO VA
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categorias")
@Entity
@Getter
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) <----
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
}
