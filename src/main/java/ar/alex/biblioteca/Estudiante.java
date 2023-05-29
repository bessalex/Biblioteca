package ar.alex.biblioteca;

import java.time.LocalDate;
import java.util.Objects;

public class Estudiante {

    private Integer padron;
    private String apellido;
    private String nombres;
    private LocalDate fechaNacimiento;

    public Estudiante(Integer padron, String apellido, String nombres, LocalDate fechaNacimiento) {
        this.padron = padron;
        this.apellido = apellido;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(padron, that.padron);
    }

}
