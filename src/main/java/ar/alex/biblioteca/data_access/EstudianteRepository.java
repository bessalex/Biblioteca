package ar.alex.biblioteca.data_access;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.business.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface EstudianteRepository  {

    void save(Estudiante estudiante);

    List<Estudiante> findAll();

    Optional<Estudiante> findByDni(Integer dni);

}
