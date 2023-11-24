package ar.alex.biblioteca.business.model;


import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class EstudianteBO {


    private final Integer dni;
    private final String apellido;
    private final String nombres;
    private final String direccion;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudianteBO that = (EstudianteBO) o;
        return Objects.equals(getDni(), that.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    public Integer getDni() {
        return this.dni;
    }


    public String getApellido() {
        return apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public EstudianteEntity mapToEntity(){
        return new EstudianteEntity(
                this.dni,
                this.apellido,
                this.nombres,
                this.direccion
        );
    }

}
