package ar.alex.biblioteca.business.model;


import ar.alex.biblioteca.business.CondicionPrestamo;
import ar.alex.biblioteca.business.exceptions.PrestamoSuperaRenovacionesException;
import ar.alex.biblioteca.business.exceptions.PrestamoVencidoException;
import ar.alex.biblioteca.business.model.Libro;
import ar.alex.biblioteca.data_access.entity.PrestamoEntity;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {

    private static int prestamoIdCounter = 0;
    private String id;
    private final Libro libro;
    private final Estudiante estudiante;
    private LocalDate fechaInicio;
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
        this.id= String.valueOf(prestamoIdCounter++);
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setNroRenovacion(int nroRenovacion) {
        this.nroRenovacion = nroRenovacion;
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
            throw new PrestamoVencidoException( String.format("Prestamo %s Vencido",this.id));
        }
        if (!this.isRenovable()){
            throw new PrestamoSuperaRenovacionesException(
                    String.format("Prestamo %s Supera nro de Renovaciones posibles",this.id));
        }
        this.fechaVencimiento = LocalDate.now().plusDays(
                this.libro.getCategoria().getMaximoDiasPrestamo(this.condiciones));
        this.nroRenovacion++;
    }


    public void expirar(){
        this.fechaVencimiento = LocalDate.now();

    }

    public LocalDate getFechaInicio() {
        return this.fechaVencimiento;
    }

    public int getNroRenovacion() {
        return this.nroRenovacion;
    }

    public String getId() {
        return id;
    }


    public PrestamoEntity mapToEntity(){
        PrestamoEntity prestamoEntity = new PrestamoEntity(
                this.id,
                this.libro.getIsbn(),
                this.estudiante.getDni(),
                this.fechaInicio
        );
        prestamoEntity.setFechaVencimiento(this.fechaVencimiento);
        prestamoEntity.setNroRenovacion(this.nroRenovacion);
        return prestamoEntity;
    }


}
