import ar.alex.biblioteca.business.Biblioteca;
import ar.alex.biblioteca.business.enums.CategoriaType;
import ar.alex.biblioteca.business.*;
import ar.alex.biblioteca.business.exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class BibliotecaTest {

    private Biblioteca biblioteca;

    private Libro libroClasico, libroHistoria, libroCiencia;
    private Estudiante estudiante1;
    private String isbnLibroClasico, isbnLibroCiencia;
    private int dniEstudiante1;


    @BeforeEach
    public void setUp() throws ReflectiveOperationException {
        this.biblioteca = new Biblioteca();
        this.isbnLibroClasico = "9788467037531";
        this.libroClasico = new Libro(this.isbnLibroClasico, "La Íliada",
                Categoria.create(CategoriaType.clasico.toString()),1);
        this.libroHistoria = new Libro("9789500718585",
                "El último confin de la tierra",
                Categoria.create(CategoriaType.historia.toString()), 1);

        this.isbnLibroCiencia = "9789874682734";
        this.libroCiencia = new Libro(this.isbnLibroCiencia, "El último teorema de Fermat",
                Categoria.create(CategoriaType.ciencia),1);

        this.dniEstudiante1 = 31999999;
        this.estudiante1 = (new Estudiante(31999999,"Pablo", "Aimar",
                "Río Cuarto"));
    }

    @AfterEach
    public void teardown(){
        this.biblioteca = null;
        this.libroClasico = null;
        this.libroHistoria = null;
        this.estudiante1 = null;
    }

    @Test
    @DisplayName("Incorporar Libros a Biblioteca OK")
    public void incorporarLibrosTest(){

        this.biblioteca.addLibro(this.libroClasico);

        Assertions.assertEquals(1, this.biblioteca.getLibros().size());
    }

    @Test
    @DisplayName("Intentar incorporar Libros Duplicados ")
    public void incorporarLibrosDuplicadosFailTest() throws ReflectiveOperationException {

        Libro libro = new Libro(this.isbnLibroClasico, "La Íliada",
                Categoria.create(CategoriaType.clasico),1);

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addLibro(libro);

        Assertions.assertEquals(1, this.biblioteca.getLibros().size());
        Assertions.assertEquals(libro,this.biblioteca.getLibros().get(0));

    }

    @Test
    @DisplayName("Obtener Libros por Categoria OK")
    public void getLibrosPorCategoriaTest() throws ReflectiveOperationException {

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addLibro(this.libroHistoria);

        List<Libro> librosClasicos = biblioteca.getLibrosPorCategoria(Categoria.create(CategoriaType.clasico));

        Assertions.assertEquals(1, librosClasicos.size());
    }


    @Test
    @DisplayName("Obtener Libros por Categoría Vacío")
    public void getLibrosPorCategoriaVacioTest() throws ReflectiveOperationException {

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addLibro(this.libroHistoria);

        List<Libro> librosDeportes = biblioteca.getLibrosPorCategoria(Categoria.create(CategoriaType.deportes));

        Assertions.assertEquals(0, librosDeportes.size());
    }


    @Test
    @DisplayName("Obtener Libro por ISBN OK")
    public void getLibroPorIsbnOKTest() {

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addLibro(this.libroHistoria);

        Libro libro = null;
        try {
            libro = biblioteca.getLibroPorISBN(this.isbnLibroClasico);
        } catch (LibroNoPresenteException e) {
            fail(e.toString());
        }

        Assertions.assertNotSame(this.libroClasico,libro);
        Assertions.assertEquals(this.libroClasico,libro);
        Assertions.assertEquals(libro.getTitulo(),this.libroClasico.getTitulo());
        Assertions.assertEquals(libro.getAutor(), this.libroClasico.getAutor());
        Assertions.assertEquals(libro.getCategoria(), this.libroClasico.getCategoria());
        Assertions.assertEquals(libro.getCategoria(), this.libroClasico.getCategoria());
    }


    @Test
    @DisplayName("Obtener libro por ISBN Fail Test")
    public void getLibroPorIsbnFailTest(){

        this.biblioteca.addLibro(this.libroHistoria);

        Assertions.assertThrows(LibroNoPresenteException.class, () ->
                biblioteca.getLibroPorISBN(this.isbnLibroClasico));

    }


    @Test
    @DisplayName("Solicitar Préstamo OK")
    public void solicitarPrestamoOKTest(){

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addLibro(this.libroHistoria);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = null;
        try {
            prestamo = biblioteca.solicitarPrestamo(this.libroClasico,this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException |
                 PrestamoDuplicadoException | EstudianteNoPresenteException e) {
            fail(e.toString());
        }

        Assertions.assertEquals(this.libroClasico.getTitulo(), prestamo.getLibro().getTitulo());
        Assertions.assertEquals(this.libroClasico.getCategoria(), prestamo.getLibro().getCategoria());
        Assertions.assertEquals(LocalDate.now().plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO),
                prestamo.getFechaVencimiento());
    }


    @Test
    @DisplayName("Solicitar Préstamo Ya Esitente")
    public void solicitarPrestamoExisteFailTest(){

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        try {
            biblioteca.solicitarPrestamo(this.libroClasico,this.estudiante1);
        } catch (LibroNoPresenteException | EstudianteNoPresenteException |
                 PrestamoDuplicadoException | LibroSinEjemplaresException e) {
            fail(e.toString());
        }

        Assertions.assertThrows(PrestamoDuplicadoException.class,
                ()-> biblioteca.solicitarPrestamo(this.libroClasico,this.estudiante1),
                "Ya existe un prestamo de este libro y estudiante");

    }


    @Test
    @DisplayName("Solicitar Prestamo Biblioteca Vacía")

    public void solicitarPrestamoSinLibrosTest(){

        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(LibroNoPresenteException.class,
                ()-> biblioteca.solicitarPrestamo(this.libroClasico, this.estudiante1));

    }


    @Test
    @DisplayName("Solicitar Préstamo Libro No Disponible")
    public void solicitarPrestamoLibroNoDisponibleFailTest(){

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);
        Estudiante estudiante2 = new Estudiante(9999,"Saviola", "Javier Pedro", "Nuñez");
        this.biblioteca.addEstudiante(estudiante2);

        try {
            biblioteca.solicitarPrestamo(this.libroClasico,estudiante2);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException |
                 PrestamoDuplicadoException | EstudianteNoPresenteException e) {
            fail(e.toString());
        }

        Assertions.assertThrows(LibroSinEjemplaresException.class,
                ()-> biblioteca.solicitarPrestamo(this.libroClasico,this.estudiante1),
                "Libro sin Ejemplares disponibles");

    }


    @Test
    @DisplayName("Solicitar Préstamo Estudiante No Existe")
    public void solicitarPrestamoEstudianteNoExisteFailTest(){

        this.biblioteca.addLibro(this.libroClasico);

        Assertions.assertThrows(EstudianteNoPresenteException.class,
                ()-> biblioteca.solicitarPrestamo(this.libroClasico,this.estudiante1),
                "Estudiante No existe en biblioteca");

    }

    @Test
    @DisplayName("Solicitar Préstamo Estudiante nulo")
    public void solicitarPrestamoEstudianteNullFailTest(){

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> biblioteca.solicitarPrestamo(this.libroClasico,null),
                "Libro o estudiante deben informarse");

    }


    @Test
    @DisplayName("Solicitar Prestamo Libro Inexistente")
    public void solicitarPrestamoLibroInexistenteTest(){

        this.biblioteca.addLibro(this.libroHistoria);
        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(LibroNoPresenteException.class,
                () -> biblioteca.solicitarPrestamo(this.libroClasico, this.estudiante1));
    }


    @Test
    @DisplayName("Visualizar Prestamos OK")
    public void visualizarPrestadosTest(){

        this.biblioteca.addLibro(this.libroHistoria);
        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        try {
            this.biblioteca.solicitarPrestamo(this.libroClasico, this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException |
                 PrestamoDuplicadoException | EstudianteNoPresenteException e) {
            fail(e.toString());
        }

        List<String> esperados = new ArrayList<>();
        esperados.add("Título = La Íliada | Fecha Vencimiento = " +
                LocalDate.now().plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        List<String> prestados = biblioteca.getVistaPrestamos();

        Assertions.assertEquals(1,prestados.size());
        Assertions.assertEquals(esperados.get(0), prestados.get(0));
    }

    @Test
    @DisplayName("Visualizar Prestamos Vacío")
    public void visualizarPrestadosVacioTest(){

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        List<String> prestados = this.biblioteca.getVistaPrestamos();

        Assertions.assertNotNull(prestados);
        Assertions.assertEquals(0,prestados.size());
    }


    @Test
    @DisplayName("Incorporar Estudiante OK")
    public void incorporarEstudianteOKTest(){

        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertEquals(1, biblioteca.getEstudiantes().size());
    }

    @Test
    @DisplayName("Incorporar Estudiante Duplicado ")
    public void incorporarEstudianteDuplicadoTest(){

        this.biblioteca.addEstudiante(this.estudiante1);

        Estudiante estudianteDup = new Estudiante(this.dniEstudiante1,"Aimar",
                "Pablo Cesar", "Río Cuarto");

        biblioteca.addEstudiante(this.estudiante1);
        biblioteca.addEstudiante(estudianteDup);

        Assertions.assertEquals(1, biblioteca.getEstudiantes().size());
    }


    @Test
    @DisplayName("Renovar Prestamo Inexistente FailTest")
    public void renovarPrestamoNoExisteTest(){

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(RuntimeException.class,
                () -> biblioteca.renovarPrestamo(this.libroClasico, this.estudiante1),
                "Prestamo No existe en biblioteca");

    }

    @Test
    @DisplayName("Renovar Prestamo Excede Limite Renovaciones ")
    public void renovarPrestamoExcedeLimiteFailTest() {

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        try {
            this.biblioteca.solicitarPrestamo(this.libroClasico, this.estudiante1);
            this.biblioteca.renovarPrestamo(this.libroClasico, this.estudiante1);
            this.biblioteca.renovarPrestamo(this.libroClasico, this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException | PrestamoDuplicadoException |
                 EstudianteNoPresenteException | PrestamoSuperaRenovacionesException | PrestamoVencidoException e) {
            fail(e.toString());
        }

        Assertions.assertThrows(PrestamoSuperaRenovacionesException.class,
                () -> biblioteca.renovarPrestamo(this.libroClasico, this.estudiante1),
                "Prestamo Supera nro de Renovaciones posibles");

    }



    @Test
    @DisplayName("Renovar Prestamo OK")
    public void renovarPrestamoOKTest() {

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = null;
        Prestamo prestamoRenovado = null;
        try {
            prestamo = this.biblioteca.solicitarPrestamo(this.libroClasico, this.estudiante1);
            Assertions.assertNotNull(prestamo);
            prestamoRenovado = biblioteca.renovarPrestamo(this.libroClasico, this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException | PrestamoDuplicadoException |
                 EstudianteNoPresenteException | PrestamoSuperaRenovacionesException | PrestamoVencidoException e) {
            fail(e.toString());
        }

        Assertions.assertEquals(this.libroClasico.getTitulo(),prestamoRenovado.getLibro().getTitulo());
        Assertions.assertEquals(this.estudiante1,prestamo.getEstudiante());
        Assertions.assertEquals(LocalDate.now().plusDays(15L),
                prestamoRenovado.getFechaVencimiento());
    }


    @Test
    @DisplayName("Renovar Prestamo Vencido")
    public void renovarPrestamoVencidoTest() {

        this.biblioteca.addLibro(this.libroClasico);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = null;

        try {
            prestamo = this.biblioteca.solicitarPrestamo(this.libroClasico, this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException | PrestamoDuplicadoException |
                 EstudianteNoPresenteException e) {
            fail(e.toString());
        }
        Assertions.assertNotNull(prestamo);
        prestamo.expirar();

        Assertions.assertThrows(PrestamoVencidoException.class,
                () -> biblioteca.renovarPrestamo(this.libroClasico, this.estudiante1),
                "Prestamo Supera nro de Renovaciones posibles");
    }


    @Test
    @DisplayName("Solicitar Préstamo Ciencia OK")
    public void solicitarPrestamoCienciaOKTest(){

        this.biblioteca.addLibro(this.libroCiencia);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = null;
        try {
            prestamo = biblioteca.solicitarPrestamo(this.libroCiencia,this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException |
                 PrestamoDuplicadoException | EstudianteNoPresenteException e) {
            fail(e.toString());
        }

        Assertions.assertEquals(this.libroCiencia.getTitulo(), prestamo.getLibro().getTitulo());
        Assertions.assertEquals(this.libroCiencia.getCategoria(), prestamo.getLibro().getCategoria());
        Assertions.assertEquals(LocalDate.now().plusDays(CondicionPrestamo.MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA),
                prestamo.getFechaVencimiento());
    }


    @Test
    @DisplayName("Renovar Prestamo Ciencia OK")
    public void renovarPrestamoCienciaOKTest() {

        this.biblioteca.addLibro(this.libroCiencia);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = null;
        Prestamo prestamoRenovado = null;
        try {
            prestamo = this.biblioteca.solicitarPrestamo(this.libroCiencia, this.estudiante1);
            Assertions.assertNotNull(prestamo);
            prestamoRenovado = biblioteca.renovarPrestamo(this.libroCiencia, this.estudiante1);
        } catch (LibroNoPresenteException | LibroSinEjemplaresException | PrestamoDuplicadoException |
                 EstudianteNoPresenteException | PrestamoSuperaRenovacionesException | PrestamoVencidoException e) {
            fail(e.toString());
        }

        Assertions.assertEquals(this.libroCiencia.getTitulo(),prestamoRenovado.getLibro().getTitulo());
        Assertions.assertEquals(this.estudiante1,prestamo.getEstudiante());
        Assertions.assertEquals(LocalDate.now().plusDays(CondicionPrestamo.MAXIMO_DIAS_PRESTAMO_CATEGORIA_CIENCIA),
                prestamoRenovado.getFechaVencimiento());
    }



}
