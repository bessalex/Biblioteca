package ar.alex.biblioteca.api.dto;


import ar.alex.biblioteca.business.Prestamo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
@AllArgsConstructor
public class PrestamoDto {

    private final String id;
    private final String isbn;
    private final Integer dni;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento ;
    private int nroRenovacion;

}
