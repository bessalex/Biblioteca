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
    @Test
    public void incorporarLibrosTest(){

        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("La Íliada", Categoria.CLASICO);

        biblioteca.addLibro(libro);

        Assertions.assertEquals(1, biblioteca.getLibros().size());
    }

    @Test
    public void incorporarLibrosDuplicadosFailTest(){
        // No se van a incorporar duplicados

        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("La Íliada", Categoria.CLASICO);
        Libro libro2 = new Libro("La Íliada", Categoria.CLASICO);

        biblioteca.addLibro(libro);
        biblioteca.addLibro(libro2);

        Assertions.assertEquals(1, biblioteca.getLibros().size());
        Assertions.assertEquals(libro,biblioteca.getLibros().get(0));

    }

    // ===== 2. Dado una categoría permita mostrar los libros de esa categoría. ====
    @Test
    public void mostrarLibrosPorCategoriaTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addLibro(new Libro("La Íliada", Categoria.CLASICO));
        biblioteca.addLibro(new Libro("El último confin de la tierra",Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("La Odisea",Categoria.CLASICO));

        List<Libro> librosClasicos = biblioteca.getLibrosPorCategoria(Categoria.CLASICO);

        Assertions.assertEquals(2, librosClasicos.size());
    }

    // === 2. Dado una categoría permita mostrar los libros de esa categoría. ===
    @Test
    public void mostrarLibrosPorCategoriaVacioTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addLibro(new Libro("La Íliada",Categoria.CLASICO));
        biblioteca.addLibro(new Libro("El último confin de la tierra",Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("La Odisea",Categoria.CLASICO));

        List<Libro> librosDeportes = biblioteca.getLibrosPorCategoria(Categoria.DEPORTE);

        Assertions.assertEquals(0, librosDeportes.size());
    }

    /*
 ====   3. Un estudiante pueda solicitar prestado un libro y que su fecha de devolución sea dentro
    de los siguientes 15 días, teniendo presente que exista un libro disponible.   =================
     */
    @Test
    public void solicitarPrestamoOKTest(){
        // crear libros en biblioteca
        Biblioteca biblioteca = new Biblioteca();
        String tituloLibro = "La Íliada";
        Libro libroAlquilado = new Libro(tituloLibro,Categoria.CLASICO);
        biblioteca.addLibro(libroAlquilado);
        biblioteca.addLibro(new Libro("El último confin de la tierra",Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("La Odisea",Categoria.CLASICO));

        // Crear estudiante
        Estudiante estudiante = new Estudiante(86606,
                "Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        // Prestamo solicitado a libro existente
        Prestamo prestamo =  biblioteca.solicitarPrestamo("La Íliada",estudiante,
                LocalDate.of(2023,05,14));

        Assertions.assertEquals(libroAlquilado.getTitulo(), prestamo.getLibro().getTitulo());
        Assertions.assertEquals(libroAlquilado.getCategoria(), prestamo.getLibro().getCategoria());
        Assertions.assertEquals(LocalDate.of(2023,05,29), prestamo.getFechaVencimiento());
    }

    @Test
    public void solicitarPrestamoSinLibrosTest(){
        Biblioteca biblioteca = new Biblioteca();

        Estudiante estudiante = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Prestamo prestamo =  biblioteca.solicitarPrestamo("La Íliada",estudiante, LocalDate.now());

        Assertions.assertNull(prestamo);
    }

    @Test
    public void solicitarPrestamoLibroInexistenteTest(){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.addLibro(new Libro("El último confin de la tierra",Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("La Odisea",Categoria.CLASICO));

        Estudiante estudiante = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Prestamo prestamo =  biblioteca.solicitarPrestamo("Un Libro",estudiante, LocalDate.now());

        Assertions.assertNull(prestamo);

    }


    /*
 ===== 4. El usuario de este aplicativo pueda visualizar los libros que se encuentran prestados
     y su fecha de devolución.                                                         ==============
     */
    @Test
    public void visualizarPrestadosTest(){
        // Cargar libros a Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.addLibro(new Libro("La Íliada",Categoria.CLASICO));
        biblioteca.addLibro(new Libro("El último confin de la tierra",Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("La Odisea",Categoria.CLASICO));
        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));
        Estudiante estudiante2 = new Estudiante(90888,"Ariel", "Ortega",
                LocalDate.of(1976, 11, 3));
        // generar Prstamos
        biblioteca.solicitarPrestamo("El último confin de la tierra", estudiante1,
                LocalDate.of(2023,05,14));
        biblioteca.solicitarPrestamo("La Íliada", estudiante2,
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
        biblioteca.addLibro(new Libro("La Íliada",Categoria.CLASICO));
        biblioteca.addLibro(new Libro("El último confin de la tierra",Categoria.HISTORIA));
        biblioteca.addLibro(new Libro("La Odisea",Categoria.CLASICO));
        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));
        Estudiante estudiante2 = new Estudiante(90888,"Ariel", "Ortega",
                LocalDate.of(1976, 11, 3));

        List<String> esperados = new ArrayList<>();
        esperados.add("Título = El último confin de la tierra | Fecha Vencimiento = " +
                LocalDate.of(2023,05,14).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        List<String> prestados = biblioteca.getVistaPrestamos();

        Assertions.assertNotNull(prestados);
        Assertions.assertEquals(0,prestados.size());
    }
    /*
 ====  5. Renovación del préstamo de su libro extendiendo su fecha de devolución siempre y
     cuando no haya excedido el límite establecido de renovaciones establecidos por la biblioteca. ===
     */
    @Test
    public void renovarPrestamoNoExisteTest(){
        // Generar Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("El último confin de la tierra",Categoria.HISTORIA);
        biblioteca.addLibro(libro);
        Libro libro2 = new Libro("La Íliada",Categoria.CLASICO);
        biblioteca.addLibro(libro2);
        // generar Estudiante
        Estudiante estudiante1 = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Prestamo prestamo = biblioteca.renovarPrestamo("La Íliada", estudiante1,LocalDate.now());
        Assertions.assertNull(prestamo);

    }

    @Test
    public void renovarPrestamoExcedeLimiteFailTest(){
        Biblioteca biblioteca = new Biblioteca();

        Libro libro = new Libro("El último confin de la tierra",Categoria.HISTORIA);
        biblioteca.addLibro(libro);
        Libro libro2 = new Libro("La Íliada",Categoria.CLASICO);
        biblioteca.addLibro(libro2);

        Estudiante estudiante1 = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Prestamo prestamo = biblioteca.solicitarPrestamo("La Íliada", estudiante1,
                LocalDate.of(2023,05,25));
        Assertions.assertEquals(prestamo.getFechaVencimiento(),
                LocalDate.of(2023,05,25).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        Prestamo prestamoRenovado = biblioteca.renovarPrestamo("La Íliada",estudiante1,
                LocalDate.of(2023, 06, 01));
        Assertions.assertEquals(prestamoRenovado.getLibro(),libro2);
        Assertions.assertEquals(prestamoRenovado.getFechaVencimiento(),
                LocalDate.of(2023,06,01).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        Prestamo prestamoRenovado2 = biblioteca.renovarPrestamo("La Íliada",estudiante1,
                LocalDate.of(2023, 06, 01));
        Assertions.assertEquals(prestamoRenovado2.getLibro(),libro2);
        Assertions.assertEquals(prestamoRenovado2.getFechaVencimiento(),
                LocalDate.of(2023,06,01).plusDays(Biblioteca.MAXIMO_DIAS_PRESTAMO));

        Prestamo prestamoRenovado3 = biblioteca.renovarPrestamo("La Íliada",estudiante1,
                LocalDate.of(2023, 06, 01));
        Assertions.assertNull(prestamoRenovado3);

    }

    @Test
    public void renovarPrestamoYaPrestadoTest(){
        Biblioteca biblioteca = new Biblioteca();

        Libro libro = new Libro("El último confin de la tierra",Categoria.HISTORIA);
        biblioteca.addLibro(libro);
        Libro libro2 = new Libro("La Íliada",Categoria.CLASICO);
        biblioteca.addLibro(libro2);

        Estudiante estudiante1 = new Estudiante(86606,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Estudiante estudiante2 = new Estudiante(99999,"Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Prestamo prestamo = biblioteca.solicitarPrestamo("La Íliada", estudiante1,
                LocalDate.of(2023,05,25));

        Prestamo prsetamoRenovado = biblioteca.renovarPrestamo("La Íliada", estudiante2,
                LocalDate.of(2023,05,26));
        Assertions.assertNull(prsetamoRenovado);

    }
    @Test
    public void renovarPrestamoOKTest(){
        Biblioteca biblioteca = new Biblioteca();

        Libro libro = new Libro("El último confin de la tierra",Categoria.HISTORIA);
        biblioteca.addLibro(libro);
        Libro libro2 = new Libro("La Íliada",Categoria.CLASICO);
        biblioteca.addLibro(libro2);

        Estudiante estudiante1 = new Estudiante(86606, "Pablo", "Aimar",
                LocalDate.of(1976, 11, 3));

        Prestamo prestamo = biblioteca.solicitarPrestamo("La Íliada", estudiante1,
                LocalDate.of(2023,05,20));

        Prestamo prestamoRenovado = biblioteca.renovarPrestamo("La Íliada",
                estudiante1, LocalDate.of(2023, 06, 01));
        Assertions.assertNotNull(prestamoRenovado);
        Assertions.assertEquals(libro2.getTitulo(),prestamoRenovado.getLibro().getTitulo());
        Assertions.assertNull(prestamoRenovado.getEstudiante());
        Assertions.assertEquals(LocalDate.of(2023,06,01).plusDays(15L),
                prestamoRenovado.getFechaVencimiento());
    }

}
