import ar.alex.biblioteca.Biblioteca;
import ar.alex.biblioteca.Categoria;
import ar.alex.biblioteca.Estudiante;
import ar.alex.biblioteca.Prestamo;
import ar.alex.biblioteca.Libro;
import org.junit.jupiter.api.*;

import java.io.IOError;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaTest {

    private Biblioteca biblioteca;

    private Libro libroLaIliada;
    private Libro libroElUltimoConfin;
    private Estudiante estudiante1;
    private String isbnLaIliada;
    private int dniEstudiante1;

    @BeforeEach
    public void setUp() {
        this.biblioteca = new Biblioteca();
        this.isbnLaIliada = "9788467037531";
        this.libroLaIliada = new Libro(this.isbnLaIliada, "La Íliada", Categoria.CLASICO,1);
        this.libroElUltimoConfin = new Libro("9789500718585",
                "El último confin de la tierra",
                Categoria.HISTORIA, 1);

        this.dniEstudiante1 = 31999999;
        this.estudiante1 = (new Estudiante(31999999,"Pablo", "Aimar",
                "Río Cuarto"));
    }

    @AfterEach
    public void teardown(){
        this.biblioteca = null;
        this.libroLaIliada = null;
        this.libroElUltimoConfin = null;
    }

    @Test
    @DisplayName("Incorporar Libros a Biblioteca OK")
    public void incorporarLibrosTest(){

        this.biblioteca.addLibro(this.libroLaIliada);

        Assertions.assertEquals(1, this.biblioteca.getLibros().size());
    }

    @Test
    @DisplayName("Intentar incorporar Libros Duplicados ")
    public void incorporarLibrosDuplicadosFailTest(){

        Libro libro = new Libro(this.isbnLaIliada, "La Íliada", Categoria.CLASICO,1);

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addLibro(libro);

        Assertions.assertEquals(1, this.biblioteca.getLibros().size());
        Assertions.assertEquals(libro,this.biblioteca.getLibros().get(0));

    }

    @Test
    @DisplayName("Obtener Libros por Categoria OK")
    public void getLibrosPorCategoriaTest(){

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addLibro(this.libroElUltimoConfin);

        List<Libro> librosClasicos = biblioteca.getLibrosPorCategoria(Categoria.CLASICO);

        Assertions.assertEquals(1, librosClasicos.size());
    }


    @Test
    @DisplayName("Obtener Libros por Categoría Vacío")
    public void getLibrosPorCategoriaVacioTest(){

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addLibro(this.libroElUltimoConfin);

        List<Libro> librosDeportes = biblioteca.getLibrosPorCategoria(Categoria.DEPORTE);

        Assertions.assertEquals(0, librosDeportes.size());
    }


    @Test
    @DisplayName("Obtener Libro por ISBN OK")
    public void getLibroPorIsbnOKTest(){

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addLibro(this.libroElUltimoConfin);

        Libro libro = biblioteca.getLibroPorISBN(this.isbnLaIliada);

        Assertions.assertNotSame(this.libroLaIliada,libro);
        Assertions.assertEquals(this.libroLaIliada,libro);
        Assertions.assertEquals(libro.getTitulo(),this.libroLaIliada.getTitulo());
        Assertions.assertEquals(libro.getAutor(), this.libroLaIliada.getAutor());
        Assertions.assertEquals(libro.getCategoria(), this.libroLaIliada.getCategoria());
        Assertions.assertEquals(libro.getCategoria(), this.libroLaIliada.getCategoria());
    }


    @Test
    @DisplayName("Obtener libro por ISBN Fail Test")
    public void getLibroPorIsbnFailTest(){

        this.biblioteca.addLibro(this.libroElUltimoConfin);

        Libro libro = biblioteca.getLibroPorISBN(this.isbnLaIliada);

        Assertions.assertNull(libro);
    }


    @Test
    @DisplayName("Solicitar Préstamo OK")
    public void solicitarPrestamoOKTest(){

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addLibro(this.libroElUltimoConfin);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo =  biblioteca.solicitarPrestamo(this.libroLaIliada,this.estudiante1);

        Assertions.assertEquals(this.libroLaIliada.getTitulo(), prestamo.getLibro().getTitulo());
        Assertions.assertEquals(this.libroLaIliada.getCategoria(), prestamo.getLibro().getCategoria());
        Assertions.assertEquals(LocalDate.now().plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO),
                prestamo.getFechaVencimiento());
    }

    @Test
    @DisplayName("Solicitar Prestamo Biblioteca Vacía")

    public void solicitarPrestamoSinLibrosTest(){

        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(IllegalArgumentException.class,
                ()->{biblioteca.solicitarPrestamo(this.libroLaIliada, this.estudiante1);
        });

    }

    @Test
    @DisplayName("Solicitar Prestamo Libro Inexistente")
    public void solicitarPrestamoLibroInexistenteTest(){

        this.biblioteca.addLibro(this.libroElUltimoConfin);
        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.solicitarPrestamo(this.libroLaIliada, this.estudiante1);
                });
    }


    @Test
    @DisplayName("Visualizar Prestamos OK")
    public void visualizarPrestadosTest(){

        this.biblioteca.addLibro(this.libroElUltimoConfin);
        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addEstudiante(this.estudiante1);

        this.biblioteca.solicitarPrestamo(this.libroLaIliada, this.estudiante1);


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

        this.biblioteca.addLibro(this.libroLaIliada);
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

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addEstudiante(this.estudiante1);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {biblioteca.renovarPrestamo(this.libroLaIliada, this.estudiante1);},
                "No existe un prestamo de este libro y estudiante");

    }

    @Test
    @DisplayName("Renovar Prestamo Excede Limite Renovaciones ")
    public void renovarPrestamoExcedeLimiteFailTest(){

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = this.biblioteca.solicitarPrestamo(this.libroLaIliada, this.estudiante1);

        Prestamo prestamoRenovado = this.biblioteca.renovarPrestamo(this.libroLaIliada, this.estudiante1);

        Prestamo prestamoRenovado2 = this.biblioteca.renovarPrestamo(this.libroLaIliada, this.estudiante1);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {biblioteca.renovarPrestamo(this.libroLaIliada, this.estudiante1);},
                "Prestamo Supera nro de Renovaciones posibles");

    }



    @Test
    @DisplayName("Renovar Prestamo OK")
    public void renovarPrestamoOKTest(){

        this.biblioteca.addLibro(this.libroLaIliada);
        this.biblioteca.addEstudiante(this.estudiante1);

        Prestamo prestamo = this.biblioteca.solicitarPrestamo(this.libroLaIliada, this.estudiante1);
        System.out.println(prestamo);
        Assertions.assertNotNull(prestamo);

        Prestamo prestamoRenovado = biblioteca.renovarPrestamo(this.libroLaIliada, this.estudiante1);

        Assertions.assertEquals(this.libroLaIliada.getTitulo(),prestamoRenovado.getLibro().getTitulo());
        Assertions.assertEquals(this.estudiante1,prestamo.getEstudiante());
        Assertions.assertEquals(LocalDate.now().plusDays(15L),
                prestamoRenovado.getFechaVencimiento());
    }

}
