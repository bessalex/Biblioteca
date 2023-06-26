package ar.alex.biblioteca.business;
import ar.alex.biblioteca.business.service.EstudianteService;
import ar.alex.biblioteca.business.service.LibroService;
import ar.alex.biblioteca.business.service.PrestamoService;
import ar.alex.biblioteca.data_access.MapEstudianteRepository;
import ar.alex.biblioteca.data_access.MapLibroRepository;
import ar.alex.biblioteca.data_access.MapPrestamoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Biblioteca {

    public static final int MAXIMO_DIAS_PRESTAMO = 15;
    public static final int MAXIMO_RENOVACIONES = 2;

    private final LibroService libroService;
    private final PrestamoService prestamoService;
    private final EstudianteService estudianteService;

    public Biblioteca(){
        this.libroService = new LibroService(new MapLibroRepository());
        this.prestamoService = new PrestamoService(new MapPrestamoRepository());
        this.estudianteService = new EstudianteService(new MapEstudianteRepository());
    }

    public void addLibro(Libro libro){
            this.libroService.save(libro);
    }

    public List<Libro> getLibros() {
        return this.libroService.findAll();
    }

    public List<Libro> getLibrosPorCategoria(Categoria categoria) {
        return this.libroService.findByCategoria(categoria);
    }

    public Prestamo solicitarPrestamo(Libro libro, Estudiante estudiante) throws IllegalArgumentException {

        if (libro == null || estudiante == null) {
            throw new IllegalArgumentException("Libro o estudiante deben informarse");
        }
        Libro libroFound = ifLibroExistOrElseThrow(libro);
        Estudiante estudianteFound = ifEstudianteExistOrElseThrow(estudiante);

        if (this.prestamoService.findByLibroAndEstudiante(libro, estudiante).isPresent()){
            throw new IllegalArgumentException("Ya existe un prestamo de este libro y estudiante");
        }

        if (!libroFound.isDisponible()){
            throw new IllegalArgumentException("Libro sin Ejemplares disponibles");
        }

        Prestamo prestamo = new Prestamo(libroFound, estudianteFound);

        this.prestamoService.save(prestamo);
        libro.marcarEjemplarPrestado();
        this.libroService.update(libro);

        return prestamo;
    }

    private Estudiante ifEstudianteExistOrElseThrow(Estudiante estudiante) {
        Optional<Estudiante> estudianteFound = this.estudianteService.findByDni(estudiante.getDni());

        return estudianteFound.orElseThrow(() ->
                new IllegalArgumentException("Estudiante No existe en biblioteca"));
    }

    private Libro ifLibroExistOrElseThrow(Libro libro) {
        Optional<Libro> libroFound = this.libroService.findByIsbn(libro.getIsbn());
        return libroFound.orElseThrow((() ->
                new IllegalArgumentException("Libro No existe en biblioteca")));
    }


    public List<String> getVistaPrestamos() {
        List<String> vista = new ArrayList<>();

        for (Prestamo prestamo : this.prestamoService.findAll()) {
            vista.add(prestamo.toString());
        }
        return vista;
    }

    public Prestamo renovarPrestamo(Libro libro, Estudiante estudiante) {

        Estudiante estudianteFound = ifEstudianteExistOrElseThrow(estudiante);

        Libro libroFound = ifLibroExistOrElseThrow(libro);

        Prestamo prestamoFound = ifPrestamoExistOrElseThrow(libroFound, estudianteFound);

        if (!prestamoFound.isRenovable()){
            throw new IllegalArgumentException("Prestamo Supera nro de Renovaciones posibles");
        }

        prestamoFound.setRenovacion();
        this.prestamoService.update(prestamoFound);

        return prestamoFound;
    }

    private Prestamo ifPrestamoExistOrElseThrow(Libro libro, Estudiante estudiante) {
        Optional<Prestamo> prestamoFound = this.prestamoService.findByLibroAndEstudiante(libro, estudiante);

        return prestamoFound.orElseThrow(() ->
                new RuntimeException("Prestamo No existe en biblioteca"));
    }


    public Libro getLibroPorISBN(String isbn) {
        Optional<Libro> libroFound =this.libroService.findByIsbn(isbn);

        return libroFound.orElseThrow( () ->
                new RuntimeException("Libro inexistente en Biblioteca"));
    }


    public void addEstudiante(Estudiante estudiante) {
        this.estudianteService.save(estudiante);

    }

    public List<Estudiante> getEstudiantes() {
        return this.estudianteService.findAll();
    }
}
