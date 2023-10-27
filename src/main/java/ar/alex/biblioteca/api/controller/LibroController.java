package ar.alex.biblioteca.api.controller;

import ar.alex.biblioteca.api.dto.LibroDto;
import ar.alex.biblioteca.business.Biblioteca;
import ar.alex.biblioteca.business.Categoria;
import ar.alex.biblioteca.business.Libro;
import ar.alex.biblioteca.business.exceptions.LibroNoPresenteException;
import ar.alex.biblioteca.business.service.LibroService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibroController {


    @NonNull
    private final LibroService libroService;

    @GetMapping("/library/books/{isbn}")
    public ResponseEntity<LibroDto> getLibroByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(new LibroDto(this.libroService.findByIsbn(isbn)));
    }

    @PostMapping("/library/books")
    public ResponseEntity<String> addLibro(@RequestBody LibroDto libroDTO) {
        libroService.save(new Libro(libroDTO.getIsbn(),
                libroDTO.getTitulo(),
                Categoria.create(libroDTO.getCategoria()),
                libroDTO.getAutor()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books/")
    public ResponseEntity<List<LibroDto>> getLibros(){
        return ResponseEntity.ok(this.libroService.findAll()
                .stream()
                .map(LibroDto::new).toList());
    }


    @GetMapping("/library/books")
    public ResponseEntity<List<LibroDto>> getLibrosByCategoria(@RequestParam(value = "category", required=false) String category) {
        return ResponseEntity.ok(this.libroService.findByCategoria(category)
                .stream().map(LibroDto::new).toList());
    }
}
