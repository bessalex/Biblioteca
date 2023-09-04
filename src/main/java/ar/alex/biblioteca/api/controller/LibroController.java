package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Biblioteca;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.CategoriaFactory;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.enums.CategoriaType;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LibroController {

    private final Biblioteca biblioteca = Biblioteca.getInstance();

    @GetMapping("/library/books/{isbn}")
    public ResponseEntity<LibroDto> getLibroByIsbn(@PathVariable String isbn) throws LibroNoPresenteException {
        return ResponseEntity.ok(new LibroDto( biblioteca.getLibroPorISBN(isbn)));
    }

    @PostMapping("/library/books")
    public ResponseEntity<String> addLibro(@RequestBody LibroDto libroDTO) throws InstantiationException, IllegalAccessException {
        System.out.println("addLibro");
        biblioteca.addLibro(new Libro(libroDTO.getIsbn(),
                libroDTO.getTitulo(),
                CategoriaFactory.createCategoria(libroDTO.getCategoria()),
                libroDTO.getAutor()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books")
    public ResponseEntity<List<LibroDto>> getLibros(){
        return ResponseEntity.ok(biblioteca.getLibros()
                .stream()
                .map(LibroDto::new)
                .collect(Collectors.toList()));
    }
}
