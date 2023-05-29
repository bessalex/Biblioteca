import ar.alex.biblioteca.Biblioteca;
import ar.alex.biblioteca.Categoria;
import ar.alex.biblioteca.Libro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
