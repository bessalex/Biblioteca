package ar.alex.biblioteca.business.mapper;

import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.data_access.entity.EstudianteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstudianteBOMapper {
    EstudianteBOMapper INSTANCE = Mappers.getMapper(EstudianteBOMapper.class);

    EstudianteBO mapToEstudianteBO(EstudianteEntity estudianteEntity);

    List<EstudianteBO> mapToEstudianteBOList(List<EstudianteEntity> estudianteEntityList);

    EstudianteEntity mapToEstudianteEntity(EstudianteBO estudianteBO);

    List<EstudianteEntity> mapToEstudianteEntityList(List<EstudianteBO> estudianteBOList);
}
