package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibroController {

    private final Biblioteca biblioteca = Biblioteca.getInstance();

    @GetMapping("/library/books/{isbn}")
    public ResponseEntity<LibroDto> getLibroByIsbn(@PathVariable String isbn) throws LibroNoPresenteException {
        return ResponseEntity.ok(biblioteca.getLibroPorISBN(isbn).mapToDTO());
    }

    @PostMapping("/library/books")
    public ResponseEntity<String> addLibro(@RequestBody LibroDto libroDTO){
        biblioteca.addLibro(new Libro(libroDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books")
    public ResponseEntity<List<LibroDto>> getLibros(){
        return ResponseEntity.ok(biblioteca.getLibros());
    }
}
