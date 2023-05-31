package ar.alex.biblioteca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    public static final int MAXIMO_DIAS_PRESTAMO = 15;
    public static final int MAXIMO_RENOVACIONES = 2;

    private List<Libro> libros ;
    private List<Prestamo> prestamos;
    private List<Estudiante> estudiantes;


    public Biblioteca(){
        this.libros = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public void addLibro(Libro libro){
        if ((libro != null) && (!this.libros.contains(libro)))
            this.libros.add(libro);
    }

    public List<Libro> getLibros() {
        List<Libro> libros = new ArrayList<>();
        this.libros.forEach( libro -> libros.add(new Libro(libro.getIsbn(), libro.getTitulo(), libro.getCategoria())));
        return libros;
    }

    public List<Libro> getLibrosPorCategoria(Categoria categoria) {
        List<Libro> librosDeCategoria = new ArrayList<>();

        this.libros.forEach(libro -> {
            if (libro.getCategoria() == categoria)
                librosDeCategoria.add(new Libro(libro.getIsbn(), libro.getTitulo(),libro.getCategoria()));
        });
        return librosDeCategoria;
    }

    public Prestamo solicitarPrestamo(String isbn, Integer dniEstudiante, LocalDate fechaPrestamo) {

        Libro libroPrestar = this.searchLibroPorISBN(isbn);
        Estudiante estudiante = this.getEstudiatePorDni(dniEstudiante);

        if (libroPrestar == null || estudiante == null)
            return null;

        if (!libroPrestar.isDisponible())
            return null;

        Prestamo prestamo = new Prestamo(libroPrestar, estudiante);
        prestamo.setFechaInicio(fechaPrestamo);

        this.prestamos.add(prestamo);
        libroPrestar.setDisponible(Boolean.FALSE);

        return new Prestamo(prestamo);
    }

    private Estudiante getEstudiatePorDni(Integer dniEstudiante) {
        Estudiante estudiante = new Estudiante(dniEstudiante);
        int posEstudiante = this.estudiantes.indexOf(estudiante);

        if (posEstudiante >= 0) {
            return this.estudiantes.get(posEstudiante);
        }
        return null;

    }


    public List<String> getVistaPrestamos() {
        List<String> vista = new ArrayList<>();

        this.prestamos.forEach(prestamo -> {
            vista.add(prestamo.toString());
        });
        return vista;
    }

    public Prestamo renovarPrestamo(String isbn, Integer dniEstudiante, LocalDate fechaRenovacion) {
        Libro libro = this.searchLibroPorISBN(isbn);
        Estudiante estudiante = this.getEstudiatePorDni(dniEstudiante);

        if (libro == null || libro.isDisponible() || estudiante == null)
            return null;

        Prestamo prestamo = this.getPrestamo(libro,estudiante);
        if (prestamo == null)
            return null;

        LocalDate nuevaFechaVencimiento = prestamo.setRenovacion(fechaRenovacion);
        if (nuevaFechaVencimiento == null)
            return null;

        return new Prestamo(prestamo);
    }


    private Prestamo getPrestamo(Libro libro,Estudiante estudiante){
        Prestamo aBuscar = new Prestamo(libro, estudiante);
        int posBuscado = this.prestamos.indexOf(aBuscar);

        if (posBuscado >= 0) {
            return this.prestamos.get(posBuscado);
        }
        return null;
    }


    public Libro getLibroPorISBN(String isbn) {
        if (isbn == null)
            return null;

        Libro libro = this.searchLibroPorISBN(isbn);
        if (libro == null)
            return null;

        return new Libro(libro);
    }

    private Libro searchLibroPorISBN(String isbn) {
        Libro libro = new Libro(isbn);
        int posLibro = this.libros.indexOf(libro);

        if (posLibro >= 0) {
            return this.libros.get(posLibro);
        }
        return null;
    }


    public void addEstudiante(Estudiante estudiante) {
        if ((estudiante != null)
                && (!this.estudiantes.contains(estudiante)))
            this.estudiantes.add(estudiante);

    }

    public List<Estudiante> getEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        this.estudiantes.forEach( estudiante -> estudiantes.add(
                new Estudiante(estudiante)));
        return estudiantes;
    }
}
