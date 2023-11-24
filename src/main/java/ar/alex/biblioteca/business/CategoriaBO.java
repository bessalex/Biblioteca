package ar.alex.biblioteca.business;


import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CategoriaBO {

    private final String nombre;

}
