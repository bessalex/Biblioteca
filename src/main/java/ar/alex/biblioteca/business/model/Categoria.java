package ar.alex.biblioteca.business.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@RequiredArgsConstructor
@Table(name = "categorias")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "nombre")
    @NonNull  private final String nombre;
}
