package ar.alex.biblioteca.business.mapper;

import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.data_access.entity.LibroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroBOMapper {
    LibroBOMapper INSTANCE = Mappers.getMapper(LibroBOMapper.class);

    LibroBO mapToLibroBO(LibroEntity libroEntity);

    List<LibroBO> mapToLibroBOList(List<LibroEntity> libroEntityList);

    @Mapping(source = "categoria", target = "idCategoria", qualifiedByName = "idCategoriaMapping")
    LibroEntity mapToLibroEntity(LibroBO libroBO);

    List<LibroEntity> mapToLibroEntityList(List<LibroBO> libroBOList);

    @Named("idCategoriaMapping")
    default Integer idCategoriaMapping(Categoria categoria) {
        return categoria.getId();
    }
}
