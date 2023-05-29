package ar.alex.biblioteca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    public static final int MAXIMO_DIAS_PRESTAMO = 15;
    private final List<Libro> libros ;
    private final List<Prestamo> prestamos;


    public Biblioteca(){
        this.libros = new ArrayList<>();
        this.prestamos = new ArrayList<>();
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

    public Prestamo solicitarPrestamo(String tituloLibro, Estudiante estudiante, LocalDate fechaPrestamo) {

        Libro libroPrestar = this.getLibro(tituloLibro);

        if (libroPrestar == null)
            return null;

        if (!libroPrestar.isDisponible())
            return null;

        Prestamo prestamo = new Prestamo(libroPrestar, estudiante,fechaPrestamo);

        this.prestamos.add(prestamo);
        libroPrestar.setDisponible(Boolean.FALSE);

        return new Prestamo(prestamo);
    }

    private Libro getLibro(String titulo){
        Libro libroBuscar = new Libro(titulo);
        int posLibro = this.libros.indexOf(libroBuscar);

        if (posLibro >= 0) {
            return this.libros.get(posLibro);
        }
        return null;
    }

}
