package ar.alex.biblioteca.api.dto;


import ar.alex.biblioteca.business.Prestamo;

import java.time.LocalDate;

public class PrestamoDto {

    private final String isbn;
    private final Integer dni;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento ;
    private int nroRenovacion;

    public PrestamoDto(String isbn, Integer dni) {
        this.isbn = isbn;
        this.dni = dni;
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

    public PrestamoDto(Prestamo prestamo){
        this.isbn = prestamo.getLibro().getIsbn();
        this.dni = prestamo.getEstudiante().getDni();
        this.fechaInicio = prestamo.getFechaInicio();
        this.fechaVencimiento = prestamo.getFechaVencimiento();
        this.nroRenovacion = prestamo.getNroRenovacion();
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getDni() {
        return dni;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getNroRenovacion() {
        return nroRenovacion;
    }
}
