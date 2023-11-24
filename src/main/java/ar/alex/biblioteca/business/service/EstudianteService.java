package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.mapper.EstudianteBOMapper;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.business.exceptions.EstudianteNoPresenteException;
import ar.alex.biblioteca.data_access.EstudianteRepository;
import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EstudianteService {

    @NonNull
    private final EstudianteRepository estudianteRepository;
    @NonNull
    private final EstudianteBOMapper estudianteBOMapper;

    public void save(EstudianteBO estudianteBO) {
        this.estudianteRepository.save(this.estudianteBOMapper.mapToEstudianteEntity(estudianteBO));
    }

    public List<EstudianteBO> findAll() {
        return this.estudianteBOMapper.mapToEstudianteBOList(
                this.estudianteRepository.findAll());
    }

    public EstudianteBO findByDni(Integer dni) {
        EstudianteEntity estudianteEntity = this.estudianteRepository.findById(dni)
                        .orElseThrow(() -> new EstudianteNoPresenteException(dni));

        return this.estudianteBOMapper.mapToEstudianteBO(estudianteEntity);
    }

}
