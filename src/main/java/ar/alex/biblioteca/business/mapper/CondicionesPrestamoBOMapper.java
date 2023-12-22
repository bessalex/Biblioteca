package ar.alex.biblioteca.business.mapper;

import ar.alex.biblioteca.api.dto.CondicionesPrestamoDto;
import ar.alex.biblioteca.business.model.CondicionPrestamoBO;
import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CondicionesPrestamoBOMapper {
    CondicionesPrestamoBOMapper INSTANCE = Mappers.getMapper(CondicionesPrestamoBOMapper.class);

    CondicionPrestamoEntity toEntity(CondicionPrestamoBO condicionPrestamoBO);

    CondicionPrestamoBO toBO(CondicionPrestamoEntity condicionPrestamoEntity);
}
