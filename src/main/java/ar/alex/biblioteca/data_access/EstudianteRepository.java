package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.business.Estudiante;

import java.util.List;

public interface EstudianteRepository {


    void save(Estudiante estudiante);

    List<Estudiante> findAll();

    Estudiante findByDni(Integer dni);

}
