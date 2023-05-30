package ar.alex.biblioteca;


import java.util.Objects;

public class Libro {
    private String titulo;
    private Categoria categoria;

    private Boolean disponible;

    public Libro(String titulo){
        this.titulo = titulo;
        this.categoria = null;
        this.disponible = Boolean.TRUE;
    }

    public Libro(String titulo, Categoria categoria){
        this.titulo = titulo;
        this.categoria = categoria;
        this.disponible = Boolean.TRUE;
    }

    public Libro( Libro libro) {
        this.titulo = libro.getTitulo();
        this.categoria = libro.getCategoria();
        this.disponible = libro.isDisponible();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(titulo, libro.titulo);
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

}
