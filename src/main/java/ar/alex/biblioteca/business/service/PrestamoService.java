package ar.alex.biblioteca.business.service;

import ar.alex.biblioteca.business.Estudiante;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.Prestamo;
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

    private void save(Prestamo prestamo) {
        this.prestamoRepository.save(prestamo.mapToEntity());
    }


    public List<Prestamo> findAll() {
        List<Prestamo> prestamoList = new ArrayList<>();
        List<PrestamoEntity> prestamoEntityList = this.prestamoRepository.findAll();
        for (PrestamoEntity prestamoEntity: prestamoEntityList) {
            Libro libro = this.libroService.findByIsbn(prestamoEntity.getIsbnLibro());
            Estudiante estudiante = this.estudianteService.findByDni(prestamoEntity.getDniEstudiante());
            Prestamo prestamo = new Prestamo(libro, estudiante );
            prestamo.setFechaInicio(prestamoEntity.getFechaInicio());
            prestamo.setNroRenovacion(prestamoEntity.getNroRenovacion());
            prestamo.setFechaVencimiento(prestamoEntity.getFechaVencimiento());
            prestamoList.add(prestamo);
        }
        return prestamoList;
    }

    public Optional<Prestamo> findByIsbnAndDni(String isbn, Integer dni) {

        Optional<PrestamoEntity> prestamoEntityOptional =
                this.prestamoRepository.findByLibroAndEstudiante(isbn, dni);

        if (prestamoEntityOptional.isEmpty())
            return Optional.empty();

        Libro libro = this.libroService.findByIsbn(isbn);
        Estudiante estudiante = this.estudianteService.findByDni(dni);

        Prestamo prestamo = new Prestamo(libro, estudiante);
        prestamo.setFechaInicio(prestamoEntityOptional.get().getFechaInicio());
        prestamo.setNroRenovacion(prestamoEntityOptional.get().getNroRenovacion());
        prestamo.setFechaVencimiento(prestamoEntityOptional.get().getFechaVencimiento());
        return Optional.of(prestamo);
    }

    public Prestamo alta(String isbnLibro, Integer dniEstudiante) {

        if (isbnLibro == null || dniEstudiante == null) {
            throw new IllegalArgumentException("ISBN y DNI deben informarse");
        }

        Libro libroFound = this.libroService.findByIsbn(isbnLibro);
        Estudiante estudianteFound = this.estudianteService.findByDni(dniEstudiante);

        if (this.findByIsbnAndDni(isbnLibro, dniEstudiante).isPresent()){
            throw new PrestamoDuplicadoException("Ya existe un prestamo de este libro y estudiante");
        }

        if (!libroFound.isDisponible()){
            throw new LibroSinEjemplaresException(String.format("Libro isbn: %s sin ejemplares disponibles", libroFound.getIsbn()));
        }

        Prestamo prestamo = new Prestamo(libroFound, estudianteFound);

        this.save(prestamo);
        libroFound.marcarEjemplarPrestado();
        this.libroService.update(libroFound);

        return prestamo;
    }

    public Prestamo renovar(String idPrestamo) {

        Optional<PrestamoEntity> prestamoEntityOptional = this.prestamoRepository.findById(idPrestamo);

        if (prestamoEntityOptional.isEmpty())
            throw new PrestamoNoPresenteException(idPrestamo);

        Libro libro = this.libroService.findByIsbn(prestamoEntityOptional.get().getIsbnLibro());
        Estudiante estudiante = this.estudianteService.findByDni(prestamoEntityOptional.get().getDniEstudiante());

        Prestamo prestamo = new Prestamo(libro, estudiante);
        prestamo.setFechaInicio(prestamoEntityOptional.get().getFechaInicio());
        prestamo.setNroRenovacion(prestamoEntityOptional.get().getNroRenovacion());
        prestamo.setFechaVencimiento(prestamoEntityOptional.get().getFechaVencimiento());

        prestamo.renovar();
        this.update(prestamo);

        return prestamo;
    }



    public void update(Prestamo prestamo) {
        this.prestamoRepository.update(prestamo.mapToEntity());
    }
}
