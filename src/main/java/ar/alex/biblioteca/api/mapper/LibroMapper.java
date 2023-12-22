package ar.alex.biblioteca.api.mapper;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.business.model.LibroBO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    LibroMapper INSTANCE = Mappers.getMapper(LibroMapper.class);
    @Mapping(source = "categoria", target = "categoria", qualifiedByName = "categoriaMapping")
    LibroBO mapToLibroBO(LibroDto libroDto);

    @Mapping(source = "categoria", target = "categoria", qualifiedByName = "categoriaMapping")
    List<LibroBO> mapToLibroBOList(List<LibroDto> libroDtoList);

    @Mapping(source = "categoria", target = "categoria", qualifiedByName = "nombreCategoriaMapping")
    LibroDto mapToLibroDto(LibroBO libroBO);

    @Mapping(source = "categoria", target = "categoria", qualifiedByName = "nombreCategoriaMapping")
    List<LibroDto> mapToLibroDtoList(List<LibroBO> libroBOList);

    @Named("categoriaMapping")
    default Categoria mapCategoria(String nombreCategoria) {
        return Categoria.builder().nombre(nombreCategoria).build();
    }

    @Named("nombreCategoriaMapping")
    default String mapNombreCategoria(Categoria categoria) {
        return categoria.getNombre();
    }
}
