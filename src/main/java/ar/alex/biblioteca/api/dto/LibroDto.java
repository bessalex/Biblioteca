package ar.alex.biblioteca.api.dto;

import ar.alex.biblioteca.business.Libro;

public class LibroDto {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;

    public LibroDto(String titulo, String autor, String isbn, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
    }

    public LibroDto(Libro libro){
        this.isbn = libro.getIsbn();
        this.autor = libro.getAutor();
        this.titulo = libro.getTitulo();
        this.categoria = libro.getCategoria().getName();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
