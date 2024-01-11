package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Table(name="libros")
@AllArgsConstructor
public class LibroEntity implements Serializable {
    @Id
    private  final String isbn;
    private  String titulo;
    private  String autor;

      @Column(name = "id_categoria")
      private  Integer idCategoria;

  //  @OneToOne <----
  //  @JoinColumn(name = "id_categoria", referencedColumnName = "id")
  //  private Categoria categoria;

    @Column(name = "ejemplares_disponibles")
    private  int ejemplaresDisponibles;


}
