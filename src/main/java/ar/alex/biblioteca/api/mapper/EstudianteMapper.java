package ar.alex.biblioteca.api.mapper;

import ar.alex.biblioteca.api.dto.EstudianteDto;
import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.business.model.LibroBO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EstudianteMapper {

    EstudianteBO mapToEstudianteBO(EstudianteDto estudianteDto);

    List<EstudianteBO> mapToEstudianteBOList(List<EstudianteDto> estudianteDtoList);

    EstudianteDto mapToEstudianteDto(EstudianteBO estudianteBO);

    List<EstudianteDto> mapToEstudianteDtoList(List<EstudianteBO> estudianteBOList);
}
