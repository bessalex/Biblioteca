package ar.alex.biblioteca.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EstudianteDto {

    private final Integer dni;
    private String apellido;
    private String nombres;
    private String direccion;

}
