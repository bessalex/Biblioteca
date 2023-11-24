package ar.alex.biblioteca.api.mapper;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.model.Categoria;
import ar.alex.biblioteca.business.model.LibroBO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface LibroMapper {
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
