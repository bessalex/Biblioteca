package ar.alex.biblioteca.business.model;


import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

public class Estudiante {


    private final Integer dni;
    private final String apellido;
    private final String nombres;
    private final String direccion;

    public Estudiante(Integer dni, String apellido, String nombres, String direccion) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombres = nombres;
        this.direccion = direccion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(getDni(), that.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    public Integer getDni() {
        return this.dni;
    }


    public Estudiante(Estudiante estudiante){
        this.dni = estudiante.getDni();
        this.apellido = estudiante.getApellido();
        this.nombres = estudiante.getNombres();
        this.direccion = estudiante.getDireccion();
    }

    public Estudiante(EstudianteEntity estudianteEntity){
        this.dni = estudianteEntity.getDni();
        this.apellido = estudianteEntity.getApellido();
        this.nombres = estudianteEntity.getNombres();
        this.direccion = estudianteEntity.getDireccion();
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
