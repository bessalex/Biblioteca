package ar.alex.biblioteca;

import java.util.Objects;

public class Libro {
    private String titulo;

    public Libro(String titulo){
        this.titulo = titulo.toLowerCase();
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
}
