package ar.alex.biblioteca;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    List<Libro> libros ;


    public Biblioteca(){
        this.libros = new ArrayList<>();
    }

    public void addLibro(Libro libro){
        if ((libro != null) && (!this.libros.contains(libro)))
            this.libros.add(libro);
    }

    public List<Libro> getLibros() {
        List<Libro> libros = new ArrayList<>();
        this.libros.forEach( libro -> libros.add(new Libro(libro.getTitulo(), libro.getCategoria())));
        return libros;
    }

    public List<Libro> getLibrosPorCategoria(Categoria categoria) {
        List<Libro> librosDeCategoria = new ArrayList<>();

        this.libros.forEach(libro -> {
            if (libro.getCategoria() == categoria)
                librosDeCategoria.add(new Libro(libro.getTitulo(),libro.getCategoria()));
        });
        return librosDeCategoria;
    }


}
