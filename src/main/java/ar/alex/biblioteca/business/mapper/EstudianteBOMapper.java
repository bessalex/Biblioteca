package ar.alex.biblioteca.business.mapper;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EstudianteBOMapper {

    EstudianteBO mapToEstudianteBO(EstudianteEntity estudianteEntity);

    List<EstudianteBO> mapToEstudianteBOList(List<EstudianteEntity> estudianteEntityList);

    EstudianteEntity mapToEstudianteEntity(EstudianteBO estudianteBO);

    List<EstudianteEntity> mapToEstudianteEntityList(List<EstudianteBO> estudianteBOList);
}
