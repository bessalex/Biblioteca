package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Builder
@Table(name = "categorias")
@Entity
@Getter
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;

}
