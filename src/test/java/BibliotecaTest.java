import ar.alex.biblioteca.Biblioteca;
import ar.alex.biblioteca.Categoria;
import ar.alex.biblioteca.Estudiante;
import ar.alex.biblioteca.Prestamo;
import ar.alex.biblioteca.Libro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaTest {


    // ==== 1. Incorporar libros y que los mismos no estén duplicados por el título. =================
    // ==== 1m. Incorporar un libro a la biblioteca. Cada libro tiene un título, un código ISBN
    //          y no puede estar registrado más de una vez.
    @Test
    public void incorporarLibrosTest(){

        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("9788467037531", "La Íliada");

        biblioteca.addLibro(libro);

        Assertions.assertEquals(1, biblioteca.getLibros().size());
    }

    @Test
    public void incorporarLibrosDuplicadosFailTest(){
        // No se van a incorporar duplicados

        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("9788467037531", "La Íliada");
        Libro libro2 = new Libro("9788467037531", "La Íliade");

        biblioteca.addLibro(libro);
        biblioteca.addLibro(libro2);

        Assertions.assertEquals(1, biblioteca.getLibros().size());
        Assertions.assertEquals(libro,biblioteca.getLibros().get(0));

    }

    // ===== 2. Dado una categoría permita mostrar los libros de esa categoría. ====
    @Test
    public void mostrarLibrosPorCategoriaTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addLibro(new Libro("9788467037531","La Íliada", Categoria.CLASICO));
        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra", Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea",Categoria.CLASICO));

        List<Libro> librosClasicos = biblioteca.getLibrosPorCategoria(Categoria.CLASICO);

        Assertions.assertEquals(2, librosClasicos.size());
    }

    // === 2. Dado una categoría permita mostrar los libros de esa categoría.                         =====
    // === 3m. Dado una categoría, permitir listar todos los libros correspondientes a esa categoría. =====
    @Test
    public void mostrarLibrosPorCategoriaVacioTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addLibro(new Libro("9788467037531","La Íliada", Categoria.CLASICO));
        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra", Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea",Categoria.CLASICO));

        List<Libro> librosDeportes = biblioteca.getLibrosPorCategoria(Categoria.DEPORTE);

        Assertions.assertEquals(0, librosDeportes.size());
    }

    /*
    ==== 2m. Dado un código ISBN, buscar y devolver los datos del libro identificado por dicho ISBN.
             Título, código ISBN, autor, y categoría.                                                 ====
     */
    @Test
    public void getLibroPorIsbnOKTest(){
        Biblioteca biblioteca = new Biblioteca();
        String isbn = "9788467037531";
        Libro laIliada = new Libro(isbn,"La Íliada", Categoria.CLASICO);
        laIliada.setAutor("Homero");
        biblioteca.addLibro(laIliada);

        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra", Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea",Categoria.CLASICO));

        Libro libro = biblioteca.getLibroPorISBN(isbn);

        Assertions.assertNotSame(laIliada,libro);
        Assertions.assertEquals(laIliada,libro);
        Assertions.assertEquals(libro.getTitulo(),laIliada.getTitulo());
        Assertions.assertEquals(libro.getAutor(), laIliada.getAutor());
        Assertions.assertEquals(libro.getCategoria(), laIliada.getCategoria());
        Assertions.assertEquals(libro.getCategoria(), laIliada.getCategoria());
    }
    @Test
    public void getLibroPorIsbnFailTest(){
        Biblioteca biblioteca = new Biblioteca();
        String isbn = "9788467037531";
        Libro laIliada = new Libro(isbn,"La Íliada", Categoria.CLASICO);
        laIliada.setAutor("Homero");

        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra", Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea",Categoria.CLASICO));

        Libro libro = biblioteca.getLibroPorISBN(isbn);

        Assertions.assertNull(libro);

    }

    /*
 ====   3. Un estudiante pueda solicitar prestado un libro y que su fecha de devolución sea dentro
    de los siguientes 15 días, teniendo presente que exista un libro disponible.

       5m. Un estudiante solicita prestado un libro y su plazo límite de devolución es
       dentro de 15 días corridos, teniendo presente que exista un libro disponible.
     */
    @Test
    public void solicitarPrestamoOKTest(){
        // crear libros en biblioteca
        Biblioteca biblioteca = new Biblioteca();
        String tituloLibro = "La Íliada";
        Libro libroAlquilado = new Libro("9788467037531","La Íliada");
        biblioteca.addLibro(libroAlquilado);
        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra"));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea"));

        // Crear estudiante
        biblioteca.addEstudiante(new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));

        // Prestamo solicitado a libro existente
        Prestamo prestamo =  biblioteca.solicitarPrestamo("9788467037531",31999999,
                LocalDate.of(2023,05,14));

        Assertions.assertEquals(libroAlquilado.getTitulo(), prestamo.getLibro().getTitulo());
        Assertions.assertEquals(libroAlquilado.getCategoria(), prestamo.getLibro().getCategoria());
        Assertions.assertEquals(LocalDate.of(2023,05,29), prestamo.getFechaVencimiento());
    }

    @Test
    public void solicitarPrestamoSinLibrosTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));

        Prestamo prestamo =  biblioteca.solicitarPrestamo("9788467037531",31999999, LocalDate.now());

        Assertions.assertNull(prestamo);
    }

    @Test
    public void solicitarPrestamoLibroInexistenteTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra"));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea"));

        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));

        Prestamo prestamo =  biblioteca.solicitarPrestamo("1",31999999, LocalDate.now());

        Assertions.assertNull(prestamo);

    }


    /*
 ===== 4. El usuario de este aplicativo pueda visualizar los libros que se encuentran prestados
     y su fecha de devolución.

     6m. Consultar una lista de libros que se encuentran prestados y su fecha de vencimiento del préstamo.

    */
    @Test
    public void visualizarPrestadosTest(){
        // Cargar libros a Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.addLibro(new Libro("9788467037531","La Íliada"));
        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra"));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea"));

        // Crear estudiantes
        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));
        biblioteca.addEstudiante( new Estudiante(90888888,"Ariel", "Ortega",
               "Río Cuarto"));
        // generar Prstamos
        biblioteca.solicitarPrestamo("9789500718585", 31999999,
                LocalDate.of(2023,05,14));
        biblioteca.solicitarPrestamo("9788467037531", 90888888,
                LocalDate.of(2023,05,10));


        List<String> esperados = new ArrayList<>();
        esperados.add("Título = El último confin de la tierra | Fecha Vencimiento = " +
                LocalDate.of(2023,05,14).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));
        esperados.add("Título = La Íliada | Fecha Vencimiento = " +
                LocalDate.of(2023,05,10).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        List<String> prestados = biblioteca.getVistaPrestamos();

        Assertions.assertEquals(2,prestados.size());
        Assertions.assertEquals(esperados.get(0), prestados.get(0));
        Assertions.assertEquals(esperados.get(1), prestados.get(1));
    }

    @Test
    public void visualizarPrestadosVacioTest(){
        // Cargar libros a Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.addLibro(new Libro("9788467037531","La Íliada"));
        biblioteca.addLibro(new Libro("9789500718585", "El último confin de la tierra"));
        biblioteca.addLibro(new Libro("9788426106582", "La Odisea"));

        // Crear estudiantes
        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));
        biblioteca.addEstudiante( new Estudiante(90888,"Ariel", "Ortega",
               "Río Cuarto"));

        List<String> prestados = biblioteca.getVistaPrestamos();

        Assertions.assertNotNull(prestados);
        Assertions.assertEquals(0,prestados.size());
    }

    /*
 ==== 4m. Incorporar un estudiante o socio a la biblioteca registrando
          un tipo y número de documento, nombre y apellido, y domicilio. ===
     */
    @Test
    public void incorporarEstudianteOKTest(){
        Biblioteca biblioteca = new Biblioteca();

        Estudiante estudiante = new Estudiante(31999999,"Aimar",
                "Pablo Cesar", "Río Cuarto");

        biblioteca.addEstudiante(estudiante);

        Assertions.assertEquals(1, biblioteca.getEstudiantes().size());
    }

    @Test
    public void incorporarEstudianteDuplicadoTest(){
        Biblioteca biblioteca = new Biblioteca();

        Estudiante estudiante = new Estudiante(31999999,"Aimar",
                "Pablo Cesar", "Río Cuarto");

        Estudiante estudianteDup = new Estudiante(31999999,"Aimar",
                "Pablo Cesar", "Río Cuarto");

        biblioteca.addEstudiante(estudiante);
        biblioteca.addEstudiante(estudianteDup);

        Assertions.assertEquals(1, biblioteca.getEstudiantes().size());
    }

    /*
 ====  5. Renovación del préstamo de su libro extendiendo su fecha de devolución siempre y
     cuando no haya excedido el límite establecido de renovaciones establecidos por la biblioteca. ===

       7m. Renovación del préstamo de su libro extendiendo su fecha de devolución siempre y
       cuando no haya excedido el límite de renovaciones establecido por la biblioteca.           =====
     */
    @Test
    public void renovarPrestamoNoExisteTest(){
        // Generar Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("9789500718585", "El último confin de la tierra");
        biblioteca.addLibro(libro);
        Libro libro2 = new Libro("9788467037531","La Íliada");
        biblioteca.addLibro(libro2);

        // generar Estudiante
        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));

        Prestamo prestamo = biblioteca.renovarPrestamo("9788467037531", 31999999,LocalDate.now());
        Assertions.assertNull(prestamo);

    }

    @Test
    public void renovarPrestamoExcedeLimiteFailTest(){
        Biblioteca biblioteca = new Biblioteca();

        Libro libro = new Libro("9789500718585", "El último confin de la tierra");
        biblioteca.addLibro(libro);
        Libro libro2 = new Libro("9788467037531","La Íliada");
        biblioteca.addLibro(libro2);

        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));

        Prestamo prestamo = biblioteca.solicitarPrestamo("9788467037531", 31999999,
                LocalDate.of(2023,05,25));
        Assertions.assertEquals(prestamo.getFechaVencimiento(),
                LocalDate.of(2023,05,25).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        Prestamo prestamoRenovado = biblioteca.renovarPrestamo("9788467037531",31999999,
                LocalDate.of(2023, 06, 01));
        Assertions.assertEquals(prestamoRenovado.getLibro(),libro2);
        Assertions.assertEquals(prestamoRenovado.getFechaVencimiento(),
                LocalDate.of(2023,06,01).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        Prestamo prestamoRenovado2 = biblioteca.renovarPrestamo("9788467037531",31999999,
                LocalDate.of(2023, 06, 01));
        Assertions.assertEquals(prestamoRenovado2.getLibro(),libro2);
        Assertions.assertEquals(prestamoRenovado2.getFechaVencimiento(),
                LocalDate.of(2023,06,01).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        Prestamo prestamoRenovado3 = biblioteca.renovarPrestamo("9788467037531",31999999,
                LocalDate.of(2023, 06, 01));

        Assertions.assertNull(prestamoRenovado3);

    }

    @Test
    public void renovarPrestamoYaPrestadoTest(){
        Biblioteca biblioteca = new Biblioteca();

        Libro libro = new Libro("9789500718585", "El último confin de la tierra");
        biblioteca.addLibro(libro);
        Libro libro2 =  new Libro("9788467037531","La Íliada");
        biblioteca.addLibro(libro2);

        biblioteca.addEstudiante( new Estudiante(31999999,"Pablo", "Aimar",
               "Río Cuarto"));

        biblioteca.addEstudiante( new Estudiante(99999,"Pablo", "Aimar",
               "Río Cuarto"));

        Prestamo prestamo = biblioteca.solicitarPrestamo("9788467037531", 31999999,
                LocalDate.of(2023,05,25));

        Prestamo prsetamoRenovado = biblioteca.renovarPrestamo("9788467037531", 99999,
                LocalDate.of(2023,05,26));
        Assertions.assertNull(prsetamoRenovado);

    }
    @Test
    public void renovarPrestamoOKTest(){
        Biblioteca biblioteca = new Biblioteca();

        Libro libro = new Libro("9789500718585", "El último confin de la tierra");
        biblioteca.addLibro(libro);
        Libro libro2 =  new Libro("9788467037531","La Íliada");
        biblioteca.addLibro(libro2);

        biblioteca.addEstudiante(  new Estudiante(31999999, "Pablo", "Aimar",
               "Río Cuarto"));

        Prestamo prestamo = biblioteca.solicitarPrestamo("9788467037531", 31999999,
                LocalDate.of(2023,05,20));
        System.out.println(prestamo);
        Assertions.assertNotNull(prestamo);

        Prestamo prestamoRenovado = biblioteca.renovarPrestamo("9788467037531",
                31999999, LocalDate.of(2023, 06, 01));
        Assertions.assertNotNull(prestamoRenovado);
        Assertions.assertEquals(libro2.getTitulo(),prestamoRenovado.getLibro().getTitulo());
        Assertions.assertNull(prestamoRenovado.getEstudiante());
        Assertions.assertEquals(LocalDate.of(2023,06,01).plusDays(15L),
                prestamoRenovado.getFechaVencimiento());
    }

}
