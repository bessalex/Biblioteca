package ar.alex.biblioteca.data_access.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="libros")
public class LibroEntity {
    @Id
    private  String isbn;
    private  String titulo;
    private  String autor;
    private  String categoria;
    private  int ejemplares_disponibles;

    public LibroEntity(String isbn, String titulo, String autor, String categoria, int ejemplares_disponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ejemplares_disponibles = ejemplares_disponibles;
    }

    public LibroEntity() {
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getEjemplares_disponibles() {
        return ejemplares_disponibles;
    }
}
