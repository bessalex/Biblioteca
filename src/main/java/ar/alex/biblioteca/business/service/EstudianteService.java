package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.data_access.EstudianteRepository;

import java.util.List;
import java.util.Optional;

public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void save(Estudiante estudiante) {
        this.estudianteRepository.save(estudiante);
    }

    public List<Estudiante> findAll() {
        return this.estudianteRepository.findAll();
    }

    public Optional<Estudiante> findByDni(Integer dni) {
        return this.estudianteRepository.findByDni(dni);
    }



}
