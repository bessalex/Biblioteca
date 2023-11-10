package ar.alex.biblioteca.api.dto;

import ar.alex.biblioteca.business.Libro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.swing.text.html.Option;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
public class LibroDto {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;

}
