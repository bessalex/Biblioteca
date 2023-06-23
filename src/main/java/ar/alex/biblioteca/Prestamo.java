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
        this.fechaInicio = LocalDate.now();
        this.fechaVencimiento = this.fechaInicio.plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO);
    }

    public Prestamo( Prestamo prestamo) {
        this.libro = prestamo.libro;
        this.estudiante = prestamo.estudiante;
        this.fechaInicio = prestamo.fechaInicio;
        this.fechaVencimiento = prestamo.getFechaVencimiento();
        this.nroRenovacion = prestamo.getNroRenovacion();
    }

    public int getNroRenovacion() {
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

    public boolean isRenovable(){
        return (this.nroRenovacion < Biblioteca.MAXIMO_RENOVACIONES);
    }


    public void setRenovacion() {
        this.fechaVencimiento = LocalDate.now().plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO);
        this.nroRenovacion++;
    }


    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

}
