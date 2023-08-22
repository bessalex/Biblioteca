package ar.alex.biblioteca.business;


import ar.alex.biblioteca.business.exceptions.PrestamoSuperaRenovacionesException;
import ar.alex.biblioteca.business.exceptions.PrestamoVencidoException;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {

    private final Libro libro;
    private final Estudiante estudiante;
    private final LocalDate fechaInicio;
    private final CondicionPrestamo condiciones;
    private LocalDate fechaVencimiento ;

    private int nroRenovacion;

    public Prestamo(Libro libro, Estudiante estudiante) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.nroRenovacion = 0;
        this.condiciones = new CondicionPrestamo();
        this.fechaInicio = LocalDate.now();
        this.fechaVencimiento = this.fechaInicio.plusDays(
                this.libro.getCategoria().getMaximoDiasPrestamo(this.condiciones));
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

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(getLibro(), prestamo.getLibro()) && Objects.equals(getEstudiante(), prestamo.getEstudiante());
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(getLibro(), getEstudiante());
    }

    private boolean isRenovable(){
        if (this.isDisponible())
            return (this.nroRenovacion < this.condiciones.getMaximoCantRenovacion());

        return false;
    }

    private boolean isDisponible(){
        return this.fechaVencimiento.isAfter(LocalDate.now());
    }

    public void renovar() throws PrestamoSuperaRenovacionesException, PrestamoVencidoException {
        if (!this.isDisponible()){
            throw new PrestamoVencidoException("Prestamo Vencido");
        }
        if (!this.isRenovable()){
            throw new PrestamoSuperaRenovacionesException("Prestamo Supera nro de Renovaciones posibles");
        }
        this.fechaVencimiento = LocalDate.now().plusDays(
                this.libro.getCategoria().getMaximoDiasPrestamo(this.condiciones));
        this.nroRenovacion++;
    }


    public void expirar(){
        this.fechaVencimiento = LocalDate.now();

    }

}
