package ar.alex.biblioteca.api.mapper;

import ar.alex.biblioteca.api.dto.PrestamoDto;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.business.model.PrestamoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {
    PrestamoMapper INSTANCE = Mappers.getMapper(PrestamoMapper.class);

    @Mapping(source = "libro", target = "isbn", qualifiedByName = "isbnLibroMapping")
    @Mapping(source = "estudiante", target = "dni", qualifiedByName = "dniEstudianteMapping")
    PrestamoDto mapToPrestamoDto(PrestamoBO prestamoBO);

    @Named("isbnLibroMapping")
    default String mapIsbnLibro(LibroBO libro) {
        return libro.getIsbn();
    }

    @Named("dniEstudianteMapping")
    default Integer mapDniEstudiante(EstudianteBO estudianteBO) {
        return estudianteBO.getDni();
    }
}
