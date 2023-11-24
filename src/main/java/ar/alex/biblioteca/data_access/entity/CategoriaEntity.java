package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@RequiredArgsConstructor
@Table(name = "categorias")
@Data
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "nombre")
    private  String nombre;
}
