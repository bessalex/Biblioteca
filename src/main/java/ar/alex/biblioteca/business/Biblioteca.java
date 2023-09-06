package ar.alex.biblioteca.business;
import ar.alex.biblioteca.business.exceptions.*;
import ar.alex.biblioteca.business.service.*;
import ar.alex.biblioteca.data_access.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Biblioteca {


    public static final int MAXIMO_DIAS_PRESTAMO = 15;
    public static final int MAXIMO_RENOVACIONES = 2;
    private final LibroService libroService;
    private final PrestamoService prestamoService;
    private final EstudianteService estudianteService;

    private static Biblioteca instance;

    @Bean
    public static Biblioteca getInstance(){
        if (instance == null){
            instance = new Biblioteca();
        }
        return instance;
    }


    public Biblioteca(){
        this.libroService = new LibroService(new MapLibroRepository());
        this.prestamoService = new PrestamoService(new MapPrestamoRepository());
        this.estudianteService = new EstudianteService(new MapEstudianteRepository());
    }

    /**
     * Agregar Libro a la biblioteca
     * @param libro omitiendo duplicados por ISBN
     */
    public void addLibro(Libro libro){
        System.out.println("addLibro--> addLibro");
        this.libroService.save(libro);
    }

    /**
     * Obtener lista de libros presentes en la biblioteca
     * @return Lista de libro
     */
    public List<Libro> getLibros() {
        return this.libroService.findAll();
    }

    /**
     * Obtener lista de libros por categoría
     * @param categoria Categoría de libro de acuerdo al enumerador
     * @see Categoria
     * @return Lista de libro de la categoría indicada
     */
    public List<Libro> getLibrosPorCategoria(Categoria categoria) {
        return this.libroService.findByCategoria(categoria);
    }

    /**
     * Conformar el prestamo solicitado por el estudiante del libro indicado.
     * La fecha de inicio es al momento de la solicitud.
     * @param libro Libro solicitado para prestamo
     * @param estudiante estudiante que solicita el prestamo
     * @return el prestamo conformado para la solicitud
     * @throws PrestamoDuplicadoException No se permite dos peticiones del mísmo libro del estudiante
     * @throws LibroSinEjemplaresException En caso de que no existan más ejemplares del libro solicitado
     * @throws EstudianteNoPresenteException Si el estudiante no registrado en biblioteca
     * @throws LibroNoPresenteException  Si el libro no existe en la biblioteca
     */
    public Prestamo solicitarPrestamo(Libro libro, Estudiante estudiante) throws LibroNoPresenteException, EstudianteNoPresenteException, PrestamoDuplicadoException, LibroSinEjemplaresException {

        if (libro == null || estudiante == null) {
            throw new IllegalArgumentException("Libro o estudiante deben informarse");
        }
        Libro libroFound = ifLibroExistOrElseThrow(libro.getIsbn());
        Estudiante estudianteFound = ifEstudianteExistOrElseThrow(estudiante.getDni());

        if (this.prestamoService.findByLibroAndEstudiante(libro, estudiante).isPresent()){
            throw new PrestamoDuplicadoException("Ya existe un prestamo de este libro y estudiante");
        }

        if (!libroFound.isDisponible()){
            throw new LibroSinEjemplaresException(String.format("Libro isbn: %s sin ejemplares disponibles", libro.getIsbn()));
        }

        Prestamo prestamo = new Prestamo(libroFound, estudianteFound);

        this.prestamoService.save(prestamo);
        libro.marcarEjemplarPrestado();
        this.libroService.update(libro);

        return prestamo;
    }

    /**
     * Obtener estudiante si está registrado,  en otro caso lanzar excepción
     * @param dni  estudiante a verificar que está incorporado a la biblioteca, por DNI
     * @return Estudiante obtenido de registrados
     * @throws EstudianteNoPresenteException En caso de no obtener el estudiante buscado.
     */
    private Estudiante ifEstudianteExistOrElseThrow(Integer dni) throws EstudianteNoPresenteException {
        Optional<Estudiante> estudianteFound = this.estudianteService.findByDni(dni);

        return estudianteFound.orElseThrow(() ->
                new EstudianteNoPresenteException(String.format("Estudiante con DNI: %d No existe en Biblioteca", dni)));
    }

    /**
     *  Obtener libro si está presente, en otro caso lanzar excepción
     * @param isbn id de libro a verificar que está presente en la biblioteca, por ISBN
     * @return Libro obtenido de los registrados
     * @throws LibroNoPresenteException En caso de no obtener el libro buscado
     */
    private Libro ifLibroExistOrElseThrow(String isbn) throws LibroNoPresenteException {
        Optional<Libro> libroFound = this.libroService.findByIsbn(isbn);
        return libroFound.orElseThrow(() ->
                new LibroNoPresenteException(String.format("Libro isbn: %s sin ejemplares disponibles", isbn)));
    }

    /**
     * Obtener vista de Prestamos
     * @return Lista
     */
    public List<String> getVistaPrestamos() {
        List<String> vista = new ArrayList<>();

        for (Prestamo prestamo : this.prestamoService.findAll()) {
            vista.add(prestamo.toString());
        }
        return vista;
    }

    /**
     * Renovar prestamo, ya solicitado vía solicitar Prestamo
     * @see Biblioteca#solicitarPrestamo(Libro, Estudiante)
     * @param libro libro objetivo de actualización de la renovación
     * @param estudiante estudiante que solicita la renovación
     * @return Prestamo renovado
     * @throws EstudianteNoPresenteException Si el estudiante no está registrado en biblioteca
     * @throws LibroNoPresenteException Si el libro no está presente en la biblioteca
     * @throws PrestamoSuperaRenovacionesException Si se supera el número de renovaciones permitidas
     */
    public Prestamo renovarPrestamo(Libro libro, Estudiante estudiante) throws PrestamoSuperaRenovacionesException, EstudianteNoPresenteException, LibroNoPresenteException, PrestamoVencidoException {

        Estudiante estudianteFound = ifEstudianteExistOrElseThrow(estudiante.getDni());
        Libro libroFound = ifLibroExistOrElseThrow(libro.getIsbn());
        Prestamo prestamoFound = ifPrestamoExistOrElseThrow(libroFound, estudianteFound);


        prestamoFound.renovar();


        this.prestamoService.update(prestamoFound);

        return prestamoFound;
    }

    /**
     * Obtener prestamo si está presente, en otro caso lanzar excepción
     * @param libro libro asociado al prestamo a verificar
     * @param estudiante estudiante asociado al prestamo a verificar
     * @return Prestamo encontrado
     * @throws RuntimeException Si no está presente
     */
    private Prestamo ifPrestamoExistOrElseThrow(Libro libro, Estudiante estudiante) {
        Optional<Prestamo> prestamoFound = this.prestamoService.findByLibroAndEstudiante(libro, estudiante);

        return prestamoFound.orElseThrow(() ->
                new RuntimeException("Prestamo No existe en biblioteca"));
    }

    /**
     * Obtener libro por el ISBN indicado
     * @param isbn número de identificación unívoca del libro
     * @return Libro obtenido
     * @throws LibroNoPresenteException Si el libro no existe
     */
    public Libro getLibroPorISBN(String isbn) throws LibroNoPresenteException {
        return ifLibroExistOrElseThrow(isbn);
    }

    /**
     * Registrar estudiante a la biblioteca
     * @param estudiante estudiante a registrar en la biblioteca
     */
    public void addEstudiante(Estudiante estudiante) {
        this.estudianteService.save(estudiante);
    }

    /**
     * Obtener la lista de estudiantes registrado en la biblioteca
     * @see Estudiante
     * @return Listado completo de estudiantes
     */
    public List<Estudiante> getEstudiantes() {
        return this.estudianteService.findAll();
    }

    /**
     * Obtener Estudiante por Dni
     * @param dni identificador del Estudiante
     * @see Estudiante
     * @return Estudiante puntual
     */
    public Estudiante getEstudianteByDni(Integer dni ) {
        return  this.ifEstudianteExistOrElseThrow(dni);
    }
}