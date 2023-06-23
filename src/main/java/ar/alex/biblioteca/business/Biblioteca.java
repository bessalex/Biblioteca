package ar.alex.biblioteca.business;
import ar.alex.biblioteca.business.service.EstudianteService;
import ar.alex.biblioteca.business.service.LibroService;
import ar.alex.biblioteca.business.service.PrestamoService;
import ar.alex.biblioteca.data_access.MapEstudianteRepository;
import ar.alex.biblioteca.data_access.MapLibroRepository;
import ar.alex.biblioteca.data_access.MapPrestamoRepository;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    public static final int MAXIMO_DIAS_PRESTAMO = 15;
    public static final int MAXIMO_RENOVACIONES = 2;

 //   private List<Libro> libros ;
 //   private final List<Prestamo> prestamos;
 //   private final List<Estudiante> estudiantes;

    private final LibroService libroService;
    private final PrestamoService prestamoService;
    private final EstudianteService estudianteService;

    public Biblioteca(){
    //    this.libros = new ArrayList<>();
        this.libroService = new LibroService(new MapLibroRepository());
        this.prestamoService = new PrestamoService(new MapPrestamoRepository());
        this.estudianteService = new EstudianteService(new MapEstudianteRepository());
    }

    public void addLibro(Libro libro){
  //      if ((libro != null) && (!this.libros.contains(libro)))
  //          this.libros.add(libro);
            this.libroService.save(libro);
    }

    public List<Libro> getLibros() {
     //   List<Libro> libros = new ArrayList<>();

                //.forEach( libro -> libros.add(new Libro(libro.getIsbn(), libro.getTitulo(), libro.getCategoria())));
        return this.libroService.findAll();
    }

    public List<Libro> getLibrosPorCategoria(Categoria categoria) {
      /*  List<Libro> librosDeCategoria = new ArrayList<>();


        this.libros.forEach(libro -> {
            if (libro.getCategoria() == categoria)
                librosDeCategoria.add(new Libro(libro.getIsbn(), libro.getTitulo(),libro.getCategoria()));
        }); */
        return this.libroService.findByCategoria(categoria);
    }

    public Prestamo solicitarPrestamo(Libro libro, Estudiante estudiante) throws IllegalArgumentException {

        if (libro == null || estudiante == null) {
            throw new IllegalArgumentException("Libro o estudiante deben informarse");
        }

        if (!libroExiste(libro)){
            throw new IllegalArgumentException("Libro inexistente en Biblioteca");
        }

        if (!estudianteExiste(estudiante)){
            throw new IllegalArgumentException("Estudiante inexistente en Biblioteca");
        }

        if (prestamoExiste(libro, estudiante)){
            throw new IllegalArgumentException("Ya existe un prestamo de este libro y estudiante");
        }

        if (!libro.isDisponible()){
            throw new IllegalArgumentException("Libro sin Ejemplares disponibles");
        }

        Prestamo prestamo = new Prestamo(libro, estudiante);

        //this.prestamos.add(prestamo);
        this.prestamoService.save(prestamo);
        libro.marcarEjemplarPrestado();
        this.libroService.update(libro);

        return prestamo;
    }

    private boolean estudianteExiste(Estudiante estudiante) {
     //   return this.estudiantes.contains(new Estudiante(estudiante));
        if (this.estudianteService.findByDni(estudiante.getDni()) != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private boolean libroExiste(Libro libro) {
        if (this.libroService.findByIsbn(libro.getIsbn()) != null)
            return Boolean.TRUE ;
        return Boolean.FALSE ;
    }

    private boolean prestamoExiste(Libro libro, Estudiante estudiante) {
        if (this.prestamoService.findByLibroAndEstudiante(libro, estudiante) != null)
            return Boolean.TRUE;

        return Boolean.FALSE;
    }




    public List<String> getVistaPrestamos() {
        List<String> vista = new ArrayList<>();

        for (Prestamo prestamo : this.prestamoService.findAll()) {
            vista.add(prestamo.toString());
        }
        return vista;
    }

    public Prestamo renovarPrestamo(Libro libro, Estudiante estudiante) {

        if (!libroExiste(libro)){
            throw new IllegalArgumentException("Libro inexistente en Biblioteca");
        }

        if (!estudianteExiste(estudiante)){
            throw new IllegalArgumentException("Estudiante inexistente en Biblioteca");
        }

        Prestamo prestamo = this.getPrestamo(libro,estudiante);
        if (prestamo == null){
            throw new IllegalArgumentException("No existe un prestamo de este libro y estudiante");
        }

        if (!prestamo.isRenovable()){
            throw new IllegalArgumentException("Prestamo Supera nro de Renovaciones posibles");
        }

        prestamo.setRenovacion();
        this.prestamoService.update(prestamo);

        return prestamo;
    }


    private Prestamo getPrestamo(Libro libro,Estudiante estudiante){
        /*Prestamo aBuscar = new Prestamo(libro, estudiante);
        int posBuscado = this.prestamos.indexOf(aBuscar);

        if (posBuscado >= 0) {
            return this.prestamos.get(posBuscado);
        }
        return null;*/
        return this.prestamoService.findByLibroAndEstudiante(libro, estudiante);
    }


    public Libro getLibroPorISBN(String isbn) {
     /*   if (isbn == null)
            return null;

        Libro libro = this.searchLibroPorISBN(isbn);
        if (libro == null)
            return null;
       */
        return this.libroService.findByIsbn(isbn);
    }
/*
    private Libro searchLibroPorISBN(String isbn) {
        Libro libro = new Libro(isbn);
        int posLibro = this.libros.indexOf(libro);

        if (posLibro >= 0) {
            return this.libros.get(posLibro);
        }
        return null;
    }
*/

    public void addEstudiante(Estudiante estudiante) {
        this.estudianteService.save(estudiante);

    }

    public List<Estudiante> getEstudiantes() {
        return this.estudianteService.findAll();
    }
}
