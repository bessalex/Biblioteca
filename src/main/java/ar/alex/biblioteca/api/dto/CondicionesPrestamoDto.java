package ar.alex.biblioteca.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder  // no va
@AllArgsConstructor
public class CondicionesPrestamoDto {
    private int idCategoria;
    private int numMaximoDiasPrestamo;
    private int numMaximoRenovaciones;
}
