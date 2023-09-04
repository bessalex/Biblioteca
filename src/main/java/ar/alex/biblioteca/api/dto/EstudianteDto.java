package ar.alex.biblioteca.api.dto;


import ar.alex.biblioteca.business.Estudiante;

public class EstudianteDto {

    private final Integer dni;
    private String apellido;
    private String nombres;
    private String direccion;

    public EstudianteDto(Integer dni, String apellido, String nombres, String direccion) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombres = nombres;
        this.direccion = direccion;
    }

    public EstudianteDto(Estudiante estudiante) {
        this.dni = estudiante.getDni();
        this.apellido = estudiante.getApellido();
        this.nombres = estudiante.getNombres();
        this.direccion = estudiante.getDireccion();
    }


    public Integer getDni() {
        return dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
