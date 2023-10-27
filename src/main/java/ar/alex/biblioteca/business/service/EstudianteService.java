package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.exceptions.EstudianteNoPresenteException;
import ar.alex.biblioteca.data_access.EstudianteRepository;
import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EstudianteService {

    @NonNull
    private final EstudianteRepository estudianteRepository;

    public void save(Estudiante estudiante) {
        this.estudianteRepository.save(estudiante.mapToEntity());
    }

    public List<Estudiante> findAll() {
        return this.estudianteRepository.findAll()
                .stream()
                .map(Estudiante::new)
                .collect(Collectors.toList());
    }

    public Estudiante findByDni(Integer dni) {
        return new Estudiante(
                this.estudianteRepository.findById(dni).orElseGet(() -> {
                    throw new EstudianteNoPresenteException(dni);
                }));
    }

}
