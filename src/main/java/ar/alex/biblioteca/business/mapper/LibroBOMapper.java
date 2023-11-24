package ar.alex.biblioteca.business.mapper;

import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LibroBOMapper {

    LibroBO mapToLibroBO(LibroEntity libroEntity);

    List<LibroBO> mapToLibroBOList(List<LibroEntity> libroEntityList);

    LibroEntity mapToLibroEntity(LibroBO libroBO);

    List<LibroEntity> mapToLibroEntityList(List<LibroBO> libroBOList);
}
