package ar.alex.biblioteca.business;


import ar.alex.biblioteca.data_access.entity.LibroEntity;

import java.util.Objects;


public class Libro {
    private final String titulo;
    private final Categoria categoria;
    private final String isbn;
    private String autor = null;

    private int ejemplares_disponibles;


    public Libro(String isbn, String titulo, Categoria categoria, int num_ejemplares){
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
        this.ejemplares_disponibles = num_ejemplares;
    }
    public Libro(String isbn, String titulo, Categoria categoria, String autor){
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.ejemplares_disponibles = 1;
    }
    public Libro( Libro libro) {
        this.titulo = libro.getTitulo();
        this.categoria = libro.getCategoria();
        this.ejemplares_disponibles = libro.ejemplares_disponibles;
        this.isbn = libro.getIsbn();
        this.autor =  libro.getAutor();
    }

    public Libro(LibroEntity libroEntity) {
        this.titulo = libroEntity.getTitulo();
        this.categoria = Categoria.create(libroEntity.getCategoria());
        this.ejemplares_disponibles = libroEntity.getEjemplares_disponibles();
        this.isbn = libroEntity.getIsbn();
        this.autor = libroEntity.getAutor();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(getIsbn(), libro.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn());
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Boolean isDisponible() {

        if (this.ejemplares_disponibles > 0 )
                return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }


    public int getEjemplares_disponibles() {
        return ejemplares_disponibles;
    }

    public void marcarEjemplarPrestado() {
        this.ejemplares_disponibles--;
    }

/*    public LibroEntity mapToEntity(){
        return new LibroEntity(
                this.isbn,
                this.titulo,
                this.autor,
                this.categoria.getName(),
                this.ejemplares_disponibles
        );
    }*/

}
