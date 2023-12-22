package ar.alex.biblioteca.business.model;


import ar.alex.biblioteca.data_access.entity.Categoria;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class LibroBO {
    private final String titulo;
    private Categoria categoria;
    private final String isbn;
    private String autor;

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


    public Categoria getIdCategoria() {
        return categoria;
    }

    public Boolean isDisponible() {

        if (this.ejemplaresDisponibles > 0 )
                return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void marcarEjemplarPrestado() {
        this.ejemplaresDisponibles--;
    }


}
