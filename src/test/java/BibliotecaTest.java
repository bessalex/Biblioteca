import ar.alex.biblioteca.Biblioteca;
import ar.alex.biblioteca.Categoria;
import ar.alex.biblioteca.Estudiante;
import ar.alex.biblioteca.Prestamo;
import ar.alex.biblioteca.Libro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
    3. Un estudiante pueda solicitar prestado un libro y que su fecha de devolución sea dentro
    de los siguientes 15 días, teniendo presente que exista un libro disponible.
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
}
