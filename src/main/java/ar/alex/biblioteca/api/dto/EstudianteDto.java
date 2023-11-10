package ar.alex.biblioteca.api.dto;


import ar.alex.biblioteca.business.Estudiante;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class EstudianteDto {

    private final Integer dni;
    private String apellido;
    private String nombres;
    private String direccion;

}
