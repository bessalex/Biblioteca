package ar.alex.biblioteca.business;


import ar.alex.biblioteca.api.dto.LibroDto;

import java.util.Objects;

public class Libro {
    private String titulo;
    private Categoria categoria;
    private final String isbn;
    private String autor = null;

    private int ejemplares_disponibles;


    public Libro(String isbn, String titulo, Categoria categoria, int num_ejemplares){
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
        this.ejemplares_disponibles = num_ejemplares;
    }

    public Libro( Libro libro) {
        this.titulo = libro.getTitulo();
        this.categoria = libro.getCategoria();
        this.ejemplares_disponibles = libro.ejemplares_disponibles;
        this.isbn = libro.getIsbn();
        this.autor =  libro.getAutor();
    }

    public Libro( LibroDto libroDto) {
        this.titulo = libroDto.getTitulo();
        this.categoria = CategoriaFactory.createCategoria(libroDto.getCategoria());
        this.ejemplares_disponibles = 1;
        this.isbn = libroDto.getIsbn();
        this.autor =  libroDto.getAutor();
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


    public void marcarEjemplarPrestado() {
        this.ejemplares_disponibles--;
    }

    public LibroDto mapToDTO(){
        LibroDto libroDTO = new LibroDto();
        libroDTO.setTitulo(this.getTitulo());
        libroDTO.setAutor(this.getAutor());
        libroDTO.setCategoria(this.getCategoria().getName());
        libroDTO.setIsbn(this.getIsbn());
        return libroDTO;
    }

}
