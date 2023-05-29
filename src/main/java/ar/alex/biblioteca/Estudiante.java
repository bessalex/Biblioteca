package ar.alex.biblioteca;

import java.time.LocalDate;

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
}
