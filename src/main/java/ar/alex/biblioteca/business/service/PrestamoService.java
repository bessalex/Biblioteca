package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.model.EstudianteBO;
import ar.alex.biblioteca.business.model.LibroBO;
import ar.alex.biblioteca.business.model.PrestamoBO;
import ar.alex.biblioteca.business.exceptions.*;
import ar.alex.biblioteca.data_access.PrestamoRepository;
import ar.alex.biblioteca.data_access.entity.PrestamoEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrestamoService {


    @NonNull
    private final PrestamoRepository prestamoRepository;
    @NonNull
    private final LibroService libroService;
    @NonNull
    private final EstudianteService estudianteService;

    private void save(PrestamoBO prestamo) {
        this.prestamoRepository.save(PrestamoEntity.builder()
                .isbnLibro(prestamo.getLibro().getIsbn())
                .dniEstudiante(prestamo.getEstudiante().getDni())
                .idCondicionPrestamo(prestamo.getCondiciones().getId())
                .fechaInicio(prestamo.getFechaInicio())
                .fechaVencimiento(prestamo.getFechaVencimiento())
                .nroRenovacion(prestamo.getNroRenovacion())
                .build());
    }


    public List<PrestamoBO> findAll() {
        List<PrestamoBO> prestamoList = new ArrayList<>();
        List<PrestamoEntity> prestamoEntityList = this.prestamoRepository.findAll();
        for (PrestamoEntity prestamoEntity: prestamoEntityList) {
            LibroBO libro = this.libroService.findByIsbn(prestamoEntity.getIsbnLibro());
            EstudianteBO estudiante = this.estudianteService.findByDni(prestamoEntity.getDniEstudiante());
            PrestamoBO prestamo = new PrestamoBO(libro, estudiante );
            prestamo.setFechaInicio(prestamoEntity.getFechaInicio());
            prestamo.setNroRenovacion(prestamoEntity.getNroRenovacion());
            prestamo.setFechaVencimiento(prestamoEntity.getFechaVencimiento());
            prestamoList.add(prestamo);
        }
        return prestamoList;
    }

    public Optional<PrestamoBO> findByIsbnAndDni(String isbn, Integer dni) {

        Optional<PrestamoEntity> prestamoEntityOptional =
                this.prestamoRepository.findByLibroAndEstudiante(isbn, dni);

        if (prestamoEntityOptional.isEmpty())
            return Optional.empty();

        LibroBO libro = this.libroService.findByIsbn(isbn);
        EstudianteBO estudiante = this.estudianteService.findByDni(dni);

        PrestamoBO prestamo = new PrestamoBO(libro, estudiante);
        prestamo.setFechaInicio(prestamoEntityOptional.get().getFechaInicio());
        prestamo.setNroRenovacion(prestamoEntityOptional.get().getNroRenovacion());
        prestamo.setFechaVencimiento(prestamoEntityOptional.get().getFechaVencimiento());
        return Optional.of(prestamo);
    }

    public PrestamoBO alta(String isbnLibro, Integer dniEstudiante) {

        if (isbnLibro == null || dniEstudiante == null) {
            throw new IllegalArgumentException("ISBN y DNI deben informarse");
        }

        LibroBO libroFound = this.libroService.findByIsbn(isbnLibro);
        EstudianteBO estudianteFound = this.estudianteService.findByDni(dniEstudiante);

        if (this.findByIsbnAndDni(isbnLibro, dniEstudiante).isPresent()){
            throw new PrestamoDuplicadoException("Ya existe un prestamo de este libro y estudiante");
        }

        if (!libroFound.isDisponible()){
            throw new LibroSinEjemplaresException(String.format("Libro isbn: %s sin ejemplares disponibles", libroFound.getIsbn()));
        }

        PrestamoBO prestamo = new PrestamoBO(libroFound, estudianteFound);

        this.save(prestamo);
        libroFound.marcarEjemplarPrestado();
        this.libroService.update(libroFound);

        return prestamo;
    }

    public PrestamoBO renovar(String idPrestamo) {

        Optional<PrestamoEntity> prestamoEntityOptional = this.prestamoRepository.findById(idPrestamo);

        if (prestamoEntityOptional.isEmpty())
            throw new PrestamoNoPresenteException(idPrestamo);

        LibroBO libro = this.libroService.findByIsbn(prestamoEntityOptional.get().getIsbnLibro());
        EstudianteBO estudiante = this.estudianteService.findByDni(prestamoEntityOptional.get().getDniEstudiante());

        PrestamoBO prestamo = new PrestamoBO(libro, estudiante);
        prestamo.setFechaInicio(prestamoEntityOptional.get().getFechaInicio());
        prestamo.setNroRenovacion(prestamoEntityOptional.get().getNroRenovacion());
        prestamo.setFechaVencimiento(prestamoEntityOptional.get().getFechaVencimiento());

        prestamo.renovar();
        this.update(prestamo);

        return prestamo;
    }



    public void update(PrestamoBO prestamo) {

        this.prestamoRepository.save()
    }
}
