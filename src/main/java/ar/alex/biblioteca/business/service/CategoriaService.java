package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.exceptions.CategoriaNoPresenteException;
import ar.alex.biblioteca.business.model.Categoria;
import ar.alex.biblioteca.data_access.CategoriaRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    @NonNull
    private final CategoriaRepository categoriaRepository;

    public void save(String categoriaNombre) {
        if (this.categoriaRepository.findByNombre(categoriaNombre).isEmpty())
            this.categoriaRepository.save(Categoria.builder()
                    .nombre(categoriaNombre)
                    .build());
    }

    public Long findIdByNombre(String nombre)  {

        Categoria categoriaEntity = this.categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new CategoriaNoPresenteException(nombre));

        return categoriaEntity.getId();
    }

}
