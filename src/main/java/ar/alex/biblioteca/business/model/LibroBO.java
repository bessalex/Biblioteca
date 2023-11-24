package ar.alex.biblioteca.business.model;


import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class LibroBO {
    private final String titulo;
    private final Categoria categoria;
    private final String isbn;
    private String autor = null;

    @Builder.Default
    private int ejemplaresDisponibles = 1 ;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibroBO libro = (LibroBO) o;
        return Objects.equals(getIsbn(), libro.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn());
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Categoria getIdCategoria() {
        return categoria;
    }

    public Boolean isDisponible() {

        if (this.ejemplaresDisponibles > 0 )
                return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }


    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void marcarEjemplarPrestado() {
        this.ejemplaresDisponibles--;
    }


}
