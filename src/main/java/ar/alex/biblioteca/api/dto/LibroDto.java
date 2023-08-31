package ar.alex.biblioteca.api.dto;

import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.CategoriaFactory;

public class LibroDto {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;


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
