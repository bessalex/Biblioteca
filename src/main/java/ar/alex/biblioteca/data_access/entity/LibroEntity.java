package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Entity
@Getter
@Table(name="libros")
@AllArgsConstructor
public class LibroEntity {
    @Id
    private  final String isbn;
    private  String titulo;
    private  String autor;
    private  String categoria;
    private  int ejemplares_disponibles;
}
