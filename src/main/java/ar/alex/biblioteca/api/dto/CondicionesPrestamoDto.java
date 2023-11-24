package ar.alex.biblioteca.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CondicionesPrestamoDto {
    private Long idCategoria;
    private int numMaximoDiasPrestamo;
    private int numMaximoRenovaciones;
}
