package ar.alex.biblioteca.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LibroDto {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;

}
