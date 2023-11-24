package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.api.dto.CondicionesPrestamoDto;
import ar.alex.biblioteca.business.model.CondicionPrestamoBO;
import ar.alex.biblioteca.business.exceptions.CategoriaNoPresenteException;
import ar.alex.biblioteca.data_access.CondicionPrestamoRepository;
import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CondicionesPrestamoService {

    @NonNull
    private final CondicionPrestamoRepository condicionPrestamoRepository;

    public void save(CondicionesPrestamoDto condicionesPrestamoDto) {
        this.condicionPrestamoRepository.save(CondicionPrestamoEntity
                .builder()
                .idCategoria(condicionesPrestamoDto.getIdCategoria())
                .numMaximoDiasPrestamo(condicionesPrestamoDto.getNumMaximoDiasPrestamo())
                .numMaximoRenovaciones(condicionesPrestamoDto.getNumMaximoRenovaciones())
                .build());
    }

    public CondicionPrestamoBO findByIdCategoria(Long idCategoria)  {

        CondicionPrestamoEntity condicionPrestamoEntity = this.condicionPrestamoRepository
                .findByIdCategoria(idCategoria)
                .orElseThrow(() -> new CategoriaNoPresenteException(idCategoria));

        return CondicionPrestamoBO.builder()
                .id(condicionPrestamoEntity.getId())
                .idCategoria(condicionPrestamoEntity.getIdCategoria())
                .numMaximoRenovaciones(condicionPrestamoEntity.getNumMaximoRenovaciones())
                .numMaximoDiasPrestamo(condicionPrestamoEntity.getNumMaximoDiasPrestamo())
                .build();
    }

}
