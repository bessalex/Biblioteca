package ar.alex.biblioteca;


import java.util.Objects;

public class Libro {
    private String titulo = null;
    private Categoria categoria= null;
    private String isbn;
    private String autor = null;
    private Boolean disponible = null;;

    public Libro(String isbn){
        this.isbn = isbn;
        this.disponible = Boolean.TRUE;
    }

    public Libro(String isbn, String titulo){
        this.isbn = isbn;
        this.titulo = titulo;
        this.disponible = Boolean.TRUE;
    }

    public Libro(String isbn, String titulo, Categoria categoria){
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
        this.disponible = Boolean.TRUE;
    }

    public Libro( Libro libro) {
        this.titulo = libro.getTitulo();
        this.categoria = libro.getCategoria();
        this.disponible = libro.isDisponible();
        this.isbn = libro.getIsbn();
        this.autor =  libro.getAutor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(this.isbn,libro.getIsbn());
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean isDisponible() {
        return this.disponible;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Boolean getDisponible() {
        return disponible;
    }
}
