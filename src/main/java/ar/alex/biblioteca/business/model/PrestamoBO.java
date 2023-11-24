package ar.alex.biblioteca.business.model;


import ar.alex.biblioteca.business.exceptions.PrestamoSuperaRenovacionesException;
import ar.alex.biblioteca.business.exceptions.PrestamoVencidoException;
import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import ar.alex.biblioteca.data_access.entity.PrestamoEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class PrestamoBO {

    private static int prestamoIdCounter = 0;
    private String id;
    private final LibroBO libro;
    private final EstudianteBO estudiante;
    private final CondicionPrestamoBO condiciones;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento ;

    @Builder.Default
    private int nroRenovacion = 0;

    public PrestamoBO(LibroBO libro, EstudianteBO estudiante, CondicionPrestamoBO condiciones) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.nroRenovacion = 0;
        this.condiciones = condiciones;
        this.fechaInicio = LocalDate.now();
        this.fechaVencimiento = this.condiciones.getFechaMaximoDiasVencimiento(this.fechaInicio);
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

    public LibroBO getLibro() {
        return libro;
    }

    public EstudianteBO getEstudiante() {
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

    private boolean isDisponible(){
        return this.fechaVencimiento.isAfter(LocalDate.now());
    }

    public void renovar() throws PrestamoSuperaRenovacionesException, PrestamoVencidoException {
        if (!this.isDisponible()){
            throw new PrestamoVencidoException( String.format("Prestamo %s Vencido",this.id));
        }
        if (!this.condiciones.isRenovable(this.nroRenovacion)){
            throw new PrestamoSuperaRenovacionesException(
                    String.format("Prestamo %s Supera nro de Renovaciones posibles",this.id));
        }
        this.fechaVencimiento = this.condiciones.getFechaMaximoDiasVencimiento(LocalDate.now());
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


}
