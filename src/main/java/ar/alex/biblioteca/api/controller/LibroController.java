package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Biblioteca;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibroController {

    private final Biblioteca biblioteca = Biblioteca.getInstance();

    @GetMapping("/library/books/{isbn}")
    public ResponseEntity<LibroDto> getLibroByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(new LibroDto( biblioteca.getLibroPorISBN(isbn)));
    }

    @PostMapping("/library/books")
    public ResponseEntity<String> addLibro(@RequestBody LibroDto libroDTO) throws ReflectiveOperationException {
        System.out.println("addLibro");
        biblioteca.addLibro(new Libro(libroDTO.getIsbn(),
                libroDTO.getTitulo(),
                Categoria.create(libroDTO.getCategoria()),
                libroDTO.getAutor()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books")
    public ResponseEntity<List<LibroDto>> getLibros(){
        return ResponseEntity.ok(biblioteca.getLibros()
                .stream()
                .map(LibroDto::new).toList());
    }


    @GetMapping("/library/books/category/{categoria}")
    public ResponseEntity<List<LibroDto>> getLibrosByCategoria(@PathVariable String categoria) throws ReflectiveOperationException {
        return ResponseEntity.ok(biblioteca.getLibrosPorCategoria(Categoria.create(categoria))
                .stream().map(LibroDto::new).toList());
    }
}
