package ar.alex.biblioteca.api.mapper;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.business.model.EstudianteBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {
    EstudianteMapper INSTANCE = Mappers.getMapper(EstudianteMapper.class);

    EstudianteBO mapToEstudianteBO(EstudianteDto estudianteDto);

    List<EstudianteBO> mapToEstudianteBOList(List<EstudianteDto> estudianteDtoList);

    EstudianteDto mapToEstudianteDto(EstudianteBO estudianteBO);

    List<EstudianteDto> mapToEstudianteDtoList(List<EstudianteBO> estudianteBOList);
}
