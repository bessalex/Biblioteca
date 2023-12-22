package ar.alex.biblioteca.api.mapper;

import ar.alex.biblioteca.api.dto.CondicionesPrestamoDto;
import ar.alex.biblioteca.api.dto.PrestamoDto;
import ar.alex.biblioteca.business.model.CondicionPrestamoBO;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.business.model.PrestamoBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CondicionesPrestamoMapper {
    CondicionesPrestamoMapper INSTANCE = Mappers.getMapper(CondicionesPrestamoMapper.class);

    CondicionesPrestamoDto toDto(CondicionPrestamoBO condicionPrestamoBO);

    CondicionPrestamoBO toBO(CondicionesPrestamoDto condicionesPrestamoDto);
}
