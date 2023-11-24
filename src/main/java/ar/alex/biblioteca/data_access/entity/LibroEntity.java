package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Builder
@Table(name="libros")
@AllArgsConstructor
public class LibroEntity {
    @Id
    private  final String isbn;
    private  String titulo;
    private  String autor;
    @Column(name = "id_categoria")
    private  Long idCategoria;
    @Column(name = "ejemplares_disponibles")
    private  int ejemplaresDisponibles;

}
