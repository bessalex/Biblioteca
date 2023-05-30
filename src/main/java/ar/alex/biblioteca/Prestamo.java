package ar.alex.biblioteca;


import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {

    private final Libro libro;
    private final Estudiante estudiante;
    private LocalDate fechaInicio = null ;
    private LocalDate fechaVencimiento = null ;

    private int nroRenovacion = 0;

    public Prestamo(Libro libro, Estudiante estudiante) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.nroRenovacion = 0;
    }

    public Prestamo( Prestamo prestamo) {
        this.libro = new Libro(prestamo.libro);
        this.estudiante = null;
        this.fechaInicio = prestamo.fechaInicio;
        this.fechaVencimiento = prestamo.getFechaVencimiento();
        this.nroRenovacion = prestamo.getNroRenovacion();
    }

    private int getNroRenovacion() {
        return this.nroRenovacion;
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

    @Override
    public String toString() {
        return "Título = " + libro.getTitulo() + " | Fecha Vencimiento = " + this.fechaVencimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(libro, prestamo.libro) &&
                Objects.equals(estudiante, prestamo.estudiante);
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio != null && this.nroRenovacion == 0 && this.fechaInicio == null) {
            this.fechaInicio = fechaInicio;
            this.fechaVencimiento = this.fechaInicio.plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO);
        }
    }

    public LocalDate setRenovacion(LocalDate fechaRenovacion) {
        if (fechaRenovacion == null
                || this.nroRenovacion >= Biblioteca.MAXIMO_RENOVACIONES
                || this.fechaVencimiento == null
                || this.fechaInicio.isAfter(fechaRenovacion)){
            return null;
        }
        this.fechaVencimiento = fechaRenovacion.plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO);
        this.nroRenovacion++;
        return this.fechaVencimiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

}
