package ar.alex.biblioteca;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Prestamo {

    private final Libro libro;
    private final Estudiante estudiante;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;

    public Prestamo(Libro libro, Estudiante estudiante, LocalDate fechaInicio) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaInicio.plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO);
    }

    public Prestamo(@NotNull Prestamo prestamo) {
        this.libro = new Libro(prestamo.libro);
        this.estudiante = null;
        this.fechaInicio = prestamo.fechaInicio;
        this.fechaVencimiento = prestamo.getFechaVencimiento();
    }

    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
}
