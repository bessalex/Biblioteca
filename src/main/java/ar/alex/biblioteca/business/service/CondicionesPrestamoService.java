package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.api.dto.CondicionesPrestamoDto;
import ar.alex.biblioteca.business.mapper.CondicionesPrestamoBOMapper;
import ar.alex.biblioteca.business.model.CondicionPrestamoBO;
import ar.alex.biblioteca.business.exceptions.CategoriaNoPresenteException;
import ar.alex.biblioteca.data_access.CondicionPrestamoRepository;
import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CondicionesPrestamoService {

    @NonNull
    private final CondicionPrestamoRepository condicionPrestamoRepository;
    @NonNull
    private final CondicionesPrestamoBOMapper condicionesPrestamoBOMapper;
    @NonNull
    private final CategoriaService categoriaService;

    public void save(CondicionPrestamoBO condicionPrestamoBO) {
        this.condicionPrestamoRepository.save(this.condicionesPrestamoBOMapper.toEntity(condicionPrestamoBO));
    }

    public CondicionPrestamoBO findById(Integer id){

        CondicionPrestamoEntity condicionPrestamoEntity;
        condicionPrestamoEntity = this.condicionPrestamoRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoPresenteException(id));

        return this.condicionesPrestamoBOMapper.toBO(condicionPrestamoEntity);
    }

    public CondicionPrestamoBO findByIdCategoria(Integer idCategoria)  {

        Categoria categoria = this.categoriaService.findById(idCategoria);

        //return this.condicionesPrestamoBOMapper.toBO(this.condicionPrestamoRepository.findByIdCategoria(categoria.getId()).get());

        return this.condicionesPrestamoBOMapper.toBO(this.condicionPrestamoRepository.findByCategoria(categoria));
    }

}
