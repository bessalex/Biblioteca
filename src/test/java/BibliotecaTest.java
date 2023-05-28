import ar.alex.biblioteca.Biblioteca;
import ar.alex.biblioteca.Libro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BibliotecaTest {


    // ==== 1. Incorporar libros y que los mismos no estén duplicados por el título. =================
    @Test
    public void incorporarLibrosTest(){

        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("La Íliada");

        biblioteca.addLibro(libro);
        List<Libro> libros =biblioteca.getLibros();

        Assertions.assertEquals(1, biblioteca.getLibros().size());
    }

    @Test
    public void incorporarLibrosDuplicadosFailTest(){
        // No se van a incorporar duplicados

        Biblioteca biblioteca = new Biblioteca();
        Libro libro = new Libro("La Íliada");
        Libro libro2 = new Libro("La Íliada");

        biblioteca.addLibro(libro);
        biblioteca.addLibro(libro2);
        List<Libro> libros = biblioteca.getLibros();

        Assertions.assertEquals(1, biblioteca.getLibros().size());
        Assertions.assertEquals(libro,biblioteca.getLibros().get(0));

    }


}
