package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Entity
@Table(name="libros")
@RequiredArgsConstructor
public class LibroEntity {
    @Id
    @NonNull
    private  final String isbn;
    @NonNull
    private  String titulo;
    @NonNull
    private  String autor;
    @NonNull
    private  String categoria;
    @NonNull
    @Setter
    private  int ejemplares_disponibles;
}
